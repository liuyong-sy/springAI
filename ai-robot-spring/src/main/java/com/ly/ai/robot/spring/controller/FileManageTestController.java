package com.ly.ai.robot.spring.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.ContentDisposition;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

import java.io.File;

/*
 * @Description:
 * @return:
 * @Author:  mock-l
 * @date:  2026/6/5 14:53
 * @FileName: FileManageTestController
 */
public class FileManageTestController {

    @GetMapping("/download")
    public Mono<Void> download(ServerHttpResponse response) {

        File file = new File("D:/10G.bin");

        response.getHeaders().setContentType(MediaType.APPLICATION_OCTET_STREAM);
        response.getHeaders().setContentDisposition(
                ContentDisposition.attachment()
                        .filename(file.getName())
                        .build()
        );

        return response.writeWith(
                DataBufferUtils.read(
                        new FileSystemResource(file),
                        response.bufferFactory(),
                        8192
                )
        );
    }
}
