package com.example.sportevents.presentation.presentationdi

import com.example.sportevents.domain.usecases.GetEventsUseCase
import com.example.sportevents.presentation.sportevents.viewmodel.SportEventsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PresentationDiModule {
    val koinModule = module {

        single {
            GetEventsUseCase(eventsRepository = get())
        }

        viewModel {
            SportEventsViewModel(getEventsUseCase = get())
        }
    }
}