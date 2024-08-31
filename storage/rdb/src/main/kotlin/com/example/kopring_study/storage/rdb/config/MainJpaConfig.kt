package com.example.kopring_study.storage.rdb.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = ["com.example.kopring_study.storage.rdb"])
@EnableJpaRepositories(basePackages = ["com.example.kopring_study.storage.rdb"])
internal class MainJpaConfig {
}