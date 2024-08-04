package com.sh8121.springtutorial.springcore.tutorial.n1_ioc_di;

public class N2_DependencyInjection {

    public static void main(String[] args) {
        Assembler assembler = new Assembler();
        Driver driver = assembler.getDriver();
        driver.drive();
    }

    static class Assembler {

        private Driver driver;

        public Assembler() {
//            driver = new Driver(new GLC());
            driver = new Driver(new GV70());
        }

        public Driver getDriver() {
            return driver;
        }
    }

    static class Driver {

        private Car car;

        public Driver(Car car) {
            this.car = car;
        }

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
