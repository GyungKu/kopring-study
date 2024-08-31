package com.example.kopring_study.core_api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.example.kopring_study"])
class KopringStudyApplication

fun main(args: Array<String>) {
    runApplication<KopringStudyApplication>(*args)
}