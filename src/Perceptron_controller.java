import java.util.ArrayList;

public class Perceptron_controller {
    public double[] weights;
    public double[] inputs;
    public  double tresh_hold;
    public double bias;
    public Pc_respnse responce;


    public String computatuional_process;
    public String computatuional_process_with_numbers;

    public double sum; //
    public double out; //выход нейрона

    public void compute()
    {
        int len = weights.length;
        double temp_double=0;
        StringBuilder computatuional_process_bldr = new StringBuilder();
        StringBuilder computatuional_process_with_numbers_bldr = new StringBuilder();

    //    System.out.println("Размер арххива");

        for (int i =0; i<len;i++)
        {
            temp_double += weights[i]*inputs[i];
            computatuional_process_bldr.append("W(").append(i).append(") * X(").append(i).append(")").append(" + ");


            computatuional_process_with_numbers_bldr.append(weights[i]).append(" * ").append(inputs[i]).append(" + ");
            System.out.println("Вес " + weights[i]);
            System.out.println("Инпукт " + inputs[i]);

        }


        temp_double+=bias;
        System.out.println("Темп вайлуе" + temp_double);

        computatuional_process_bldr.append("Bias");
        computatuional_process_with_numbers_bldr.append(bias);

        sum = temp_double;
        computatuional_process_bldr.append(" = summ");
        computatuional_process_with_numbers_bldr.append("= ").append(sum);

        if (sum>=tresh_hold)
        {
            this.out=1;
            computatuional_process_bldr.append(" summ >= than treshhold = 1");
            computatuional_process_with_numbers_bldr.append(" treshhold ").append(tresh_hold).append(" >=").append(sum).append("=").append(1);
        }
        else
        {
            this.out=0;
            computatuional_process_bldr.append(" summ < than treshhold = 0");
            computatuional_process_with_numbers_bldr.append(" treshhold ").append(tresh_hold).append(" <").append(sum).append("=").append(0);
        }

        String s1 = computatuional_process_bldr.toString();
        String s2=computatuional_process_with_numbers_bldr.toString();
        double sum_inp=this.sum;
        double out_in=this.out;
        this.responce = new Pc_respnse(s1,s2,sum_inp,out_in);
    }

    public Perceptron_controller(double[] weights, double[] inputs, double tresh_hold, double bias) {
        this.weights = weights;
        this.inputs = inputs;
        this.tresh_hold = tresh_hold;
        this.bias = bias;
    }

    public class Pc_respnse
    {
        public String computatuional_process;
        public String computatuional_process_with_numbers;
        public double sum; //
        public double out; //выход нейрона

        public Pc_respnse(String computatuional_process, String computatuional_process_with_numbers, double sum, double out) {
            this.computatuional_process = computatuional_process;
            this.computatuional_process_with_numbers = computatuional_process_with_numbers;
            this.sum = sum;
            this.out = out;
        }
    }




}
