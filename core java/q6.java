package assessment;

//Q6. There is an array with every element repeated twice except one. Find that element

public class q6 {
        public static void main(String[] args) {
            int[] arr1 = {2,3,4,2,3,4,6,7,5,6,7,1,9,9,1};
            int result = arr1[0];
            for (int i = 1; i < arr1.length; i++) {
                result = result ^ arr1[i];
            }
            System.out.println(result);
        }

    }
