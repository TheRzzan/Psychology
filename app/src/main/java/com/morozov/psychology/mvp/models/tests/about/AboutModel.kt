package com.morozov.psychology.mvp.models.tests.about

import com.morozov.psychology.mvp.models.tests.about.enums.*

data class AboutModel(var sex: SexEnum, var age: Int, var maritalStatus: MaritalStatusEnum,
                      var education: EducationEnum, var timeOfUse: Int, var frequencyOfUse: FrequencyOfUseEnum,
                      var timeOfPsychologistVisit: Int?, var frequencyOfTherapy: FrequencyOfTherapyEnum?,
                      var medicines: Pair<MutableList<MedicinesEnum>, String>?)