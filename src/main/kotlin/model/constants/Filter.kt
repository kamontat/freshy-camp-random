package model.constants

import model.Student

/**
 * @author kamontat
 * @version 1.0
 * @since Sat 29/Jul/2017 - 10:56 PM
 */
enum class Filter(val func: (Student) -> Boolean) {
    SEX_F({
        it.sex == model.Sex.FEMALE
    }),
    SEX_M({
        it.sex == model.Sex.MALE
    }),
    DEPARTMENT_SKE({
        it.department == model.Department.SKE
    }),
    DEPARTMENT_CPE({
        it.department == model.Department.CPE
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