package com.sh8121.springtutorial.springcore.tutorial.n1_ioc_di;

public class N1_MultipleRoleClass {

    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.drive();
    }

    static class Driver {

        //        private Car car = new GV70();
        private Car car = new GLC();

        public void drive() {
            car.excel();
            car.breaks();
            car.excel();
        }
    }

    interface Car {

        void excel();

        void breaks();
    }

    static class GV70 implements Car {

        @Override
        public void excel() {
            System.out.println("GV70 Excel");
        }

        @Override
        public void breaks() {
            System.out.println("GV70 Break");
        }
    }

    static class GLC implements Car {

        @Override
        public void excel() {
            System.out.println("GLC Excel");
        }

        @Override
        public void breaks() {
            System.out.println("GLC Break");
        }
    }
}
