#ifndef __VECTORLIB_HPP__
#define __VECTORLIB_HPP__


class VectorLib{
    public:
    int x;
    int y;

    VectorLib(int x, int y);

    void addVector(VectorLib vector);
    void substractVector(VectorLib vector);
    void divideByVector(VectorLib vector);
    void multiplyByVector(VectorLib vector);
};    

#endif // __VECTORLIB_HPP__
