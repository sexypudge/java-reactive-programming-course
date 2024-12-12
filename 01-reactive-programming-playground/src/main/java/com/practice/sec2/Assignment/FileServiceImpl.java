package com.practice.sec2.Assignment;

import com.vinsguru.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public class FileServiceImpl implements FileService {

    private static final Path PATH = Path.of("src/main/resources/sec02");

    @Override
    public Mono<String> read(String fileName) {
        log.info("reading file {}", fileName);

        return Mono.fromCallable(() -> {
            try {
                return Files.readString(PATH.resolve(fileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public Mono<Void> write(String fileName, String content) {
        return Mono.fromRunnable(() -> {
            try {
                Files.writeString(PATH.resolve(fileName), content);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public Mono<Void> delete(String fileName) {
        return null;
    }

    public static void main(String[] args) {
        FileService fileService = new FileServiceImpl();

        fileService.write("test.txt", "This is a test file")
                .subscribe(Util.subscriber());

        fileService.read("test.txt")
                .subscribe(
                        Util.subscriber()
                );
    }
}
