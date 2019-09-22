package com.morozov.psychology.utility

class DateConverter {

    companion object {
        fun getStringMonth(month: String): String =
            when(month) {
                "01" -> "Января"
                "02" -> "Февраля"
                "03" -> "Марта"
                "04" -> "Апреля"
                "05" -> "Мая"
                "06" -> "Июня"
                "07" -> "Июля"
                "08" -> "Августа"
                "09" -> "Сентября"
                "10" -> "Октября"
                "11" -> "Ноября"
                "12" -> "Декабря"
                else -> "Other"
            }

        fun getStringMonthSimple(month: String): String =
            when(month) {
                "01" -> "Январь"
                "02" -> "Февраль"
                "03" -> "Март"
                "04" -> "Апрель"
                "05" -> "Май"
                "06" -> "Июнь"
                "07" -> "Июль"
                "08" -> "Август"
                "09" -> "Сентябрь"
                "10" -> "Октябрь"
                "11" -> "Ноябрь"
                "12" -> "Декабрь"
                else -> "Other"
            }
    }
}