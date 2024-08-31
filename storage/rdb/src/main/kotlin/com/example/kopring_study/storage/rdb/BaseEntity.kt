package com.example.kopring_study.storage.db_main

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.ZonedDateTime

@MappedSuperclass
internal abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @CreationTimestamp
    val createdAt: ZonedDateTime? = null

    @UpdateTimestamp
    var updateBy: ZonedDateTime? = null
        protected set
}