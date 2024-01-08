package com.wyl.reflect.util

import java.lang.reflect.GenericArrayType
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.lang.reflect.TypeVariable
import java.util.Arrays

fun printType(type: Type) {
    println("---------------------------")
    printInnerType(type, 0)
    println("===========================")
}

private fun printInnerType(type: Type?, indentation: Int) {
    when (type) {
        is Class<*> -> {
            println("\t".repeat(indentation) + "【Class】 typeName: ${type.typeName}, name: ${type.name}")
            println("\t".repeat(indentation + 1) + "1、typeParameters: ${Arrays.toString(type.typeParameters)}")
            type.typeParameters.forEach {
                printInnerType(it, indentation + 1)
            }
            println("\t".repeat(indentation + 1) + "2、componentType: ${type.componentType}")
        }

        is TypeVariable<*> -> {
            println("\t".repeat(indentation) + "【TypeVariable】 typeName: ${type.typeName}, name: ${type.name}")
            type.bounds.forEachIndexed { index, bound ->
                println("\t".repeat(indentation + 1) + "${index + 1}、bound: $bound")
            }
        }

        is ParameterizedType -> {
            println("\t".repeat(indentation) + "【ParameterizedType】 typeName: ${type.typeName}")
            println("\t".repeat(indentation) + "1、rawType:")
            printInnerType(type.rawType, indentation + 1)
            println("\t".repeat(indentation) + "2、ownerType: ")
            printInnerType(type.ownerType, indentation + 1)
            println("\t".repeat(indentation) + "3、actualTypeArguments: ")
            type.actualTypeArguments.forEach {
                printInnerType(it, indentation + 1)
            }
        }

        is GenericArrayType -> {
            println("\t".repeat(indentation) + "【GenericArrayType】 typeName: ${type.typeName}")
            println("\t".repeat(indentation) + "1、genericComponentType: ")
            printInnerType(type.genericComponentType, indentation + 1)
        }

        else -> {
            println("\t".repeat(indentation) + "null")
        }
    }
}
