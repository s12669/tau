#include "vectorlib.hpp"
#include <iostream>

using std::cout;
using std::endl;

    VectorLib::VectorLib(int newVectorX,int newVectorY){
        x = newVectorX;
        y = newVectorY;
    }

    void VectorLib::addVector(VectorLib vector){
        x = x + vector.x;
        y = y + vector.y;
    }

    void VectorLib::substractVector(VectorLib vector){
        x = x - vector.x;
        y = y - vector.y;
    }

    void VectorLib::divideByVector(VectorLib vector){
        x = x / vector.x;
        y = y / vector.y;
    }

    void VectorLib::multiplyByVector(VectorLib vector){
        x = x * vector.x;
        y = y * vector.y;
    }