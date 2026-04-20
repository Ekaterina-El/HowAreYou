package com.ekaterinael.data.mapper

interface Mapper<DTO, DB> {
    fun fromDTO(input: DTO): DB
    fun toDTO(input: DB): DTO

    fun toDTO(list: List<DB>): List<DTO>
}