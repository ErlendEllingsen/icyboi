package org.undadhorde

enum class UserFood(val foodName: String) {
    SWORDFISH("Swordfish"),
    LOBSTER("Lobster"),
    BASS("Bass"),
    SHARK("Shark"),
    MANTA_RAY("Manta ray"),
    MONKFISH("Monkfish"),
}

var activeFood = UserFood.SHARK