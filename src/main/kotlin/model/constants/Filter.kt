package model.constants

import model.Student

/**
 * @author kamontat
 * @version 1.0
 * @since Sat 29/Jul/2017 - 10:56 PM
 */
enum class Filter(val func: (Student) -> Boolean) {
    SEX_F({
        it.sex == Sex.FEMALE
    }),
    SEX_M({
        it.sex == Sex.MALE
    }),
    DEPARTMENT_SKE({
        it.department == Department.SKE
    }),
    DEPARTMENT_CPE({
        it.department == Department.CPE
    }),
    VISIBILITY_TRUE({
        it.visibility
    }),
    VISIBILITY_FALSE({
        !it.visibility
    }),
    NONE({
        true
    })
}