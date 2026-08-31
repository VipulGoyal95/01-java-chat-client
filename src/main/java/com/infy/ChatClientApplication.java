package com.infy;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatClientApplication implements CommandLineRunner {

	private ChatClient chatClient;

	// Constructor injection: Spring provides a preconfigured ChatClient.Builder
	public ChatClientApplication(ChatClient.Builder chatClientBuilder) {
		// ChatClient is auto-configured using spring.ai.openai.* properties
		this.chatClient = chatClientBuilder.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(ChatClientApplication.class, args);
	}

	/*
	 * Key Spring AI flow:
	 * - ChatClient.Builder builds a client configured from application.properties
	 * - chatClient.prompt(...) creates the user prompt
	 * - .call().content() sends request and returns model response text
	 *
	 * Current connection properties (GitHub Models, OpenAI-compatible API):
	 * - spring.ai.openai.api-key=${GITHUB_TOKEN}
	 * - spring.ai.openai.base-url=https://models.github.ai
	 * - spring.ai.openai.chat.completions-path=/inference/chat/completions
	 * - spring.ai.openai.chat.options.model=openai/gpt-4o-mini
	 */
	@Override
	public void run(String... args) throws Exception {
		// Compose the prompt text before sending it to the model

		// More Prompts
		//1.  "What types of in-flight entertainment and amenities are typically offered on long-haul flights?"
		//2.  "What are the benefits of joining an airline frequent flyer program?"
		//3.  "Suggest 3 popular travel destinations that are well connected by major airlines."
		//4.  "What seat classes are typically available on commercial airlines and how do they differ?"
		String composedPrompt = "Suggest 3 popular travel destinations that are well connected by major airlines.?";

		String response = chatClient.prompt(composedPrompt)
				.call()
				.content();
		System.out.println("\uD83E\uDD16 Response from AI: " + response);

	}

}
