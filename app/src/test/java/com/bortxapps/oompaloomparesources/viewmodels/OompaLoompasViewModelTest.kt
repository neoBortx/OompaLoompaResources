package com.bortxapps.oompaloomparesources.viewmodels

import androidx.paging.PagingData
import com.bortxapps.data.OompaLoompaInfo
import com.bortxapps.oompaloomparesources.viewmodels.GenderFilterOptions.*
import com.bortxapps.usercases.GetAllOompaLoompasUserCase
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.*
import org.junit.Assert.*

class OompaLoompasViewModelTest {

    @MockK
    lateinit var getAllOompaLoompasUserCase: GetAllOompaLoompasUserCase

    private lateinit var oompaLoompasViewModel: OompaLoompasViewModel

    private val mockFlow: Flow<PagingData<OompaLoompaInfo>> = flow {

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun start() {
        MockKAnnotations.init(this)

        every { getAllOompaLoompasUserCase.invoke() } returns mockFlow

        oompaLoompasViewModel = spyk(OompaLoompasViewModel(getAllOompaLoompasUserCase))
    }

    @Test
    fun `check the source of the flow`() {
        assertEquals(mockFlow, oompaLoompasViewModel.oompaLoompas)
    }

    @Test
    fun `change profession filter expect only the profession filter changed and not appended`() {
        val expectedProfession = "Minion"
        val expectedProfession2 = "King"
        oompaLoompasViewModel.setProfessionFilter(expectedProfession)
        assertEquals(expectedProfession, oompaLoompasViewModel.filterState.profession)

        oompaLoompasViewModel.setProfessionFilter(expectedProfession2)
        assertEquals(expectedProfession2, oompaLoompasViewModel.filterState.profession)
    }

    @Test
    fun `set gender filter to female expect gender filter updated and profession not changed`() {
        val expectedProfession = "Minion"
        oompaLoompasViewModel.setProfessionFilter(expectedProfession)
        oompaLoompasViewModel.changeGenderVisibility(Female)

        assertEquals(expectedProfession, oompaLoompasViewModel.filterState.profession)
        assertEquals("F", oompaLoompasViewModel.filterState.genderFilter)
    }

    @Test
    fun `set gender filter to male expect gender filter updated and profession not changed`() {
        val expectedProfession = "Minion"
        oompaLoompasViewModel.setProfessionFilter(expectedProfession)
        oompaLoompasViewModel.changeGenderVisibility(Male)

        assertEquals(expectedProfession, oompaLoompasViewModel.filterState.profession)
        assertEquals("M", oompaLoompasViewModel.filterState.genderFilter)
    }

    @Test
    fun `set gender filter to both expect gender filter empty and profession not changed`() {
        val expectedProfession = "Minion"
        oompaLoompasViewModel.setProfessionFilter(expectedProfession)
        oompaLoompasViewModel.changeGenderVisibility(Both)

        assertEquals(expectedProfession, oompaLoompasViewModel.filterState.profession)
        assert(oompaLoompasViewModel.filterState.genderFilter.isEmpty())
    }
}