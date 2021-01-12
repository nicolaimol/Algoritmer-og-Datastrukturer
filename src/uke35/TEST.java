package uke35;

public final class TEST {
    public static void main(String[] args) {
        Object testObject = testMethod();
        System.out.println(testObject);
        System.out.println(testObject);
    }

    static Object testMethod() {
        Object testObject = new Object() {
            int tall = 10;

            int getTall() {
                return tall;
            }

            @Override
            public String toString() {
                //return String.valueOf(tall);
                return "from object";
            }
        };

        return testObject;
    }
}
