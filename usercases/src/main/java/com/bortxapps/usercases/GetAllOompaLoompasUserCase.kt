package com.bortxapps.usercases

import com.bortxapps.respository.OompaLoompasRepository
import javax.inject.Inject

class GetAllOompaLoompasUserCase @Inject constructor(private val oompaLoompasRepository: OompaLoompasRepository) {

    operator fun invoke() = oompaLoompasRepository.getAll()
}