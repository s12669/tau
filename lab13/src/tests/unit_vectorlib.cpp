#include <vectorlib.hpp>
#include <catch.hpp>


TEST_CASE("check addition","[VectorLib][addVector]") {
    VectorLib v1(8,7);
    VectorLib v2(4,0);

    SECTION("two vectors") {
        REQUIRE(&v1 != NULL);
        REQUIRE(&v2 != NULL);
    }
    
    SECTION("adding vectors") {
        v1.addVector(v2);
        REQUIRE(v1.x == 12);
        REQUIRE(v1.y == 7);
    }
}

TEST_CASE("check substraction","[VectorLib][addVector]") {
    VectorLib v1(5,2);
    VectorLib v2(2,1);

    SECTION("two vectors") {
        REQUIRE(&v1 != NULL);
        REQUIRE(&v2 != NULL);
    }
    
    SECTION("substracting vectors") {
        v1.substractVector(v2);
        REQUIRE(v1.x == 3);
        REQUIRE(v1.y == 1);
    }
}

TEST_CASE("check multiplication","[VectorLib][multipleByVector]") {
    VectorLib v1(1,3);
    VectorLib v2(5,2);

    SECTION("two vectors") {
        REQUIRE(&v1 != NULL);
        REQUIRE(&v2 != NULL);
    }
    
    SECTION("multiplying vectors") {
        v1.multiplyByVector(v2);
        REQUIRE(v1.x == 5);
        REQUIRE(v1.y == 6);
    }
}

SCENARIO("Scietific calculations") {
    VectorLib v1(6,3);
    VectorLib v2(1,1);
    VectorLib v3(2,2);

    GIVEN("3 vectors were successfully created") {
        REQUIRE(&v1 != NULL);
        REQUIRE(&v2 != NULL);
        REQUIRE(&v3 != NULL);

        WHEN("Added second vector to first and myltiplied result with 3rd vector") {
            REQUIRE_NOTHROW(v1.addVector(v2));
            REQUIRE_NOTHROW(v1.multiplyByVector(v3));
            
            THEN("The first vector should have value (14,8)") {
                REQUIRE(v1.x == 14);
                REQUIRE(v1.y == 8);
            }
        }
    }
}

