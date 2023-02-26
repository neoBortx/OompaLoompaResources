package com.bortxapps.usercases

import com.bortxapps.respository.OompaLoompasRepository
import javax.inject.Inject

class GetOompaLoompaDetailUserCase @Inject constructor(private val oompaLoompasRepository: OompaLoompasRepository) {

    suspend operator fun invoke(id: Int) = oompaLoompasRepository.getDetail(id)
}