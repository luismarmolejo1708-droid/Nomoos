package com.tdea.mcp_banco_server;

import com.tdea.mcp_banco_server.service.BancoService;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbacks;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class McpBancoServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(McpBancoServerApplication.class, args);
    }

    @Bean
    public List<ToolCallback> tools(BancoService bancoService) {
        return List.of(ToolCallbacks.from(bancoService));
    }
}
