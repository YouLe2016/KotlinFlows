package com.wyl.generics.part2;

class CEO extends Manager {
}

class Manager extends Employee {
}

class Employee {
    int countLegs() {
        return 1;
    }
}
