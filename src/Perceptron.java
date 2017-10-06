import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Perceptron {



    public boolean is_first = true;
    //Окна_форточки
    public ArrayList<JButton> add_buttons;
    //Панели
    public JPanel the_whole_perceptron_jp; //Панелька для целикового персептрона (верхняя часть + нижняя часть)
    public JPanel p_jf; //панель для текущего персептрона (верхняя часть: кнопки, веса и инпуты, тхета, выход)
    public JPanel inputs_jf; //панель для инпутов, лабел + текстовое поле
    //Дополнительная нижняя панелька
    public JPanel bottom_jpanel; //Bias  + edit text - ход выполнения работы
    public JPanel bias_jp; //панелька для компонентов bias
    public JLabel bias_label; //Лабел для bias
    public JTextField bias_tf; // текст филд для Bias
    public JPanel calc_process_jp;//панелька для компонентов процесса выполнения расчетов и итога.
    public JLabel calc_process_label;

    public JTextArea calc_process_ta;


    //Панелька для кнопочек
    public JPanel buttons_jp;
    public JButton add_x_btn;
    public JButton del_x_btn;

    //Аррей листы панелек
    public ArrayList<JPanel> rows_of_input_jpanels;
    //Персептрон и его картинка (и отображение трешхолда)
    public JPanel pict_jf; // панель для картинки персептрона
    //Панелька для вывода результата подсчета персептрона
    public JPanel result_jf; //панелька для результата персептрона
    public JLabel result_label; //
    public JPanel calc_process_jf; //панелька для отображения хода вычисления персептрона


    //Аррей-листы текстовых элементов
    public ArrayList<Two_comps> jfc_compt_list; //компоненты для входных данных


    //Treshhold
    public JLabel tresh_lb;
    public JTextField tresh_tf;


    //out and summ
    public JLabel out_lb;
    public JLabel sum_lb;
    public JTextField out_tf;
    public JTextField sum_tf;
    public JPanel out_pl;
    public JPanel sum_pl;
    public JPanel sum_and_out;






    public JFrame link_for_main_frame; //линк на главное окно
    public JPanel link_for_this_palne;//линк на панельку в которой данный персептрон лежит.


    public Perceptron(JFrame link_for_main_frame,JPanel link_for_this_palne, boolean is_first) { //Конструктор персептрона, перадаем линки на окно и основную панель.
        this.link_for_main_frame = link_for_main_frame; //Линк на окно
        this.link_for_this_palne = link_for_this_palne; //линк на фрейм - нужно для рисования линий в нем и передачи параметров - элемент класса -  Jpanel_for_line
        this.is_first = is_first;


        //Создаем лист двойных компонентов
        this.jfc_compt_list = new ArrayList<Two_comps>();
        this.rows_of_input_jpanels = new ArrayList<JPanel>();

        //Делаем предварительную разметку
               // и размещаем начальные элементы которые не будут убираться ни при каких обстоятельствах.
        this.p_jf = new JPanel(); //Создаем основную обрамляющую панель


        //панель для кпнопочек создания или удаления панельки
        buttons_jp = new JPanel(new GridLayout(2,1));

        add_x_btn = new JButton(new Add_button_pressed(this));
        add_x_btn.setText("ADD");
        add_x_btn.setToolTipText("Нажмите для того, что бы добавить новую пару input/weight X and W");

        del_x_btn = new JButton(new Del_button_pressed(this));
        del_x_btn.setText("DEL");
        del_x_btn.setToolTipText("Нажмите для того, что бы удалить пару input/weight X and W. ВНИМАНИЕ последняя пара будет удалена, только в случае если у персептрона есть данные от предидущего персептрона");


        buttons_jp.add(add_x_btn);
        buttons_jp.add(del_x_btn);
        //this.buttons_jp.setLayout(new BoxLayout(this.buttons_jp, BoxLayout.Y_AXIS));



            //Панель для входов
        this.inputs_jf = new JPanel();
        this.inputs_jf.setLayout(new BoxLayout(this.inputs_jf, BoxLayout.Y_AXIS)); // вертикальное размещение элементов лейблы





        //Тестовые инпуты для подгонки расстояния

       //лабелы для
        jfc_compt_list.add(this.add_row_of_inputs(jfc_compt_list.size(),true,this.is_first));
        jfc_compt_list.add(this.add_row_of_inputs(jfc_compt_list.size(),false,this.is_first));





        for (Two_comps twc_i: jfc_compt_list)
        {
            rows_of_input_jpanels.add(this.add_two_row_of_inputs_to_the_panels_2(twc_i));
        }


        for (JPanel jpnl_i:rows_of_input_jpanels)
        {
            inputs_jf.add(jpnl_i);
        }







        //Добавляем треугольничик персептрона с заданием тхеты (треШхолда)
        pict_jf = new JPanel();
        pict_jf.setLayout(new GridBagLayout());
        this.tresh_lb = new JLabel("treshhold=");
        this.tresh_tf = new JTextField(1);
        tresh_tf.setText("0");
        pict_jf.add(this.tresh_lb);
        pict_jf.add( this.tresh_tf);



  //      Jpanel_for_tringl tempo = new Jpanel_for_tringl();





        //Создаем out и summ
        //summ

        this.sum_lb = new JLabel("sum=");
        this.sum_tf = new JTextField(1);
        this.sum_pl = new JPanel();
        this.sum_pl.setLayout(new BoxLayout(this.sum_pl, BoxLayout.Y_AXIS));
        this.sum_pl.add(this.sum_lb);
        this.sum_pl.add(this.sum_tf);


        //out
        this.out_lb = new JLabel("out=");
        this.out_tf = new JTextField(1);
        this.out_pl = new JPanel();
        this.out_pl.setLayout(new BoxLayout(this.out_pl, BoxLayout.Y_AXIS));
        this.out_pl.add(this.out_lb);
        this.out_pl.add(this.out_tf);



        //добавляем их на одну панельку
        this.sum_and_out = new JPanel();
        this.sum_and_out.setLayout(new BoxLayout( this.sum_and_out, BoxLayout.Y_AXIS));
        this.sum_and_out.add(this.sum_pl);
        this.sum_and_out.add(this.out_pl);



        //Добавляем надпись OUTPUT для подсчитанного
        this.result_jf = new JPanel();
        result_jf.setLayout(new GridBagLayout());
        this.result_jf.add(this.sum_and_out);



        //Добавляем панели на основную панель персептрона
        this.p_jf.add(buttons_jp); // добавляем панельку кнопок.
        this.p_jf.add(this.inputs_jf);//панель весов и X (инпутов)
        this.p_jf.add(this.pict_jf); //Панель для трэшхолда
        this.p_jf.add(this.result_jf);// панельку вывода подсчитаного итога.

        //Создаем нижнюю панельку
        this.bottom_jpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        //Создаем bias панельку
        this.bias_jp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.bias_label = new JLabel("Bias");
        this.bias_tf = new JTextField();
        this.bias_tf.setText("0");
        this.bias_tf.setColumns(1);
        this.bias_jp.add(this.bias_label);
        this.bias_jp.add(this.bias_tf);
        //this.bias_jp.setPreferredSize(new Dimension(400,30));


        //создаем панельку
        this.calc_process_jp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        this.calc_process_label = new JLabel("Calc process");
        this.calc_process_ta = new JTextArea("Пока пустоватенько");
        this.calc_process_ta.setColumns(20);
        this.calc_process_ta.setRows(5);
        this.calc_process_jp.add(this.calc_process_label);
        this.calc_process_jp.add(this.calc_process_ta);
        //this.calc_process_jp.setPreferredSize(new Dimension(400,30));


        //добавляем панельки bias and calc_process на нижнюю панельку

       // this.bottom_jpanel.setPreferredSize(new Dimension(400,30));
        this.bottom_jpanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.bottom_jpanel.setAlignmentY(Component.TOP_ALIGNMENT);

        this.bottom_jpanel.add(this.bias_jp);
        this.bottom_jpanel.add(this.calc_process_jp);





        //Создаем обрамляющий панель
        this.the_whole_perceptron_jp = new JPanel(new BoxLayout(this.the_whole_perceptron_jp, BoxLayout.Y_AXIS));

       this.the_whole_perceptron_jp.setLayout(new GridLayout(2,1));

        this.the_whole_perceptron_jp.add(this.p_jf);
        this.the_whole_perceptron_jp.add(this.bottom_jpanel);
        this.the_whole_perceptron_jp.setAlignmentX(Component.LEFT_ALIGNMENT);
        the_whole_perceptron_jp.setAlignmentY(Component.TOP_ALIGNMENT);



        //Добавляем панель персептрона на основную панель приложения

        //the_whole_perceptron_jp.setPreferredSize(new Dimension(400,300));

        this.link_for_this_palne.add(this.the_whole_perceptron_jp);





        link_for_main_frame.validate();
        link_for_main_frame.repaint();
    }


    public double[] get_all_weights ()
    {
           int size =  this.jfc_compt_list.size();
           double[] ret_arr = new double[size];
           for (int i =0; i<size;i++)
           {
            try {
                ret_arr[i]= Double.valueOf(this.jfc_compt_list.get(i).jtf_weight.getText());
            } catch (Exception e)
            {
                JOptionPane.showMessageDialog(null, "УПППС. Ощибочка вышла! введите правильное значенеи в вес №" + i, "Ошибочка:",JOptionPane.INFORMATION_MESSAGE);
            }

           }
        return ret_arr;
    }

    public double[] get_all_xs ()
    {
        int size =  this.jfc_compt_list.size();
        double[] ret_arr = new double[size];
        for (int i =0; i<size;i++)
        {
            try {
                ret_arr[i]= Double.valueOf(this.jfc_compt_list.get(i).jtf_input.getText());
            } catch (Exception e)
            {
                JOptionPane.showMessageDialog(null, "УПППС. Ощибочка вышла! введите правильное значенеи в инпут №" + i, "Ошибочка:",JOptionPane.INFORMATION_MESSAGE);
            }
        }
        return ret_arr;
    }


    public double get_bias ()
    {
        double ret_val =0;
        try {
            ret_val= Double.valueOf(this.bias_tf.getText().toString());
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "УПППС. Ощибочка вышла! введите правильное значенеи в биас №" , "Ошибочка:",JOptionPane.INFORMATION_MESSAGE);
        }
    return ret_val;
    }

    public double get_treshhold()
    {
        double ret_val =0;
        try {
            ret_val= Double.valueOf(this.tresh_tf.getText().toString());
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "УПППС. Ощибочка вышла! введите правильное значенеи в трешхолд №" , "Ошибочка:",JOptionPane.INFORMATION_MESSAGE);
        }
        return ret_val;
    }



    public double get_sum()
    {
        double ret_val =0;
        try {
            ret_val= Double.valueOf(this.sum_tf.getText().toString());
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "УПППС. Ощибочка вышла! введите правильное значенеи в сумму №" , "Ошибочка:",JOptionPane.INFORMATION_MESSAGE);
        }
        return ret_val;
    }

    public double get_out()
    {
        double ret_val =0;
        try {
            ret_val= Double.valueOf(this.out_tf.getText().toString());
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "УПППС. Ощибочка вышла! введите правильное значенеи в out №" , "Ошибочка:",JOptionPane.INFORMATION_MESSAGE);
        }
        return ret_val;
    }


    public void set_out(double d)
    {
        this.out_tf.setText(String.valueOf(d));
    }

    public void set_sum(double d)
    {
        this.sum_tf.setText(String.valueOf(d));
    }

    public void  append_text_results (String s1, String s2)
    {
        this.calc_process_ta.setText("Формула:= ");
        this.calc_process_ta.append(s1);
        this.calc_process_ta.append("\n");
        this.calc_process_ta.append("Расчет:= ");
        this.calc_process_ta.append(s2);
    }


    public void set_first_weight (double d)
    {
        this.jfc_compt_list.get(0).jtf_input.setText(String.valueOf(d));
    }




















    public void Add_button_function()
    {

        // JOptionPane.showMessageDialog(null, "hgf", "InfoBox: " + "hgj", JOptionPane.INFORMATION_MESSAGE);
        JButton b_new = new JButton(new Test_Button_action_add_new_button(this));
        b_new.setText("ADD2");
        add_buttons.add(b_new);
        this.link_for_this_palne.add(b_new);
        JLabel l=new JLabel("Anurag jain(csanuragjain)");

        this.link_for_this_palne.add(l);
       //this.link_for_main_frame.Setx(2);
        link_for_main_frame.validate();
        link_for_main_frame.repaint();

        //next_perceptron.link_for_main_frame.pack();


    }






    public Two_comps add_row_of_inputs (int size_of_cr_elmts, boolean is_first, boolean is_in_second_perceptron) //функция для добавления готовых инпутов.
    {


        JLabel tmp_label = new JLabel();
        tmp_label.setText("Input X(" + String.valueOf(size_of_cr_elmts) + ")");


            JTextField tmp_tf = new JTextField(2);
            tmp_tf.setColumns(1);
            tmp_tf.setText("0");

        if (is_first && !is_in_second_perceptron) {
            tmp_label.setText("output Y");
            tmp_tf.setEditable(false);
                }


        JLabel tmp_label_1 = new JLabel();
        tmp_label_1.setText("Weight W(" + String.valueOf(size_of_cr_elmts) + ")");

        JTextField tmp_tf_1 = new JTextField(2);
        tmp_tf_1.setColumns(1);
        tmp_tf_1.setText("0");


        Two_comps  tw_ret = new Two_comps(tmp_label,tmp_tf,tmp_label_1,tmp_tf_1,is_first,is_in_second_perceptron);
        return tw_ret;

    }



    public JPanel add_two_row_of_inputs_to_the_panels_2 (Two_comps twc)
    {
        JPanel temp_jpanel = new JPanel();
      //  JPanel temp_jpanel_2 = new JPanel();
       temp_jpanel.setLayout(new BoxLayout(temp_jpanel, BoxLayout.X_AXIS));
        temp_jpanel.add(twc.jl_input);
        temp_jpanel.add(twc.jtf_input);
        temp_jpanel.add(Box.createRigidArea(new Dimension(15,0)));
        temp_jpanel.add(twc.jl_weight);
        temp_jpanel.add(twc.jtf_weight);

                //temp_jpanel.add(temp_jpanel_2);



        //this.inputs_jf_2.add(Box.createRigidArea(new Dimension(5,20)));  // Добавляет расстояние между штуками.
        return temp_jpanel;
    }

   class Two_comps
           //класс для хранения пар компонентов лабел и текстовое поле.
       // плюс есть флаги - является ли он первым (тогда его удалять нельзя, т.к. хоть один вход должен быть)
       // Второй флаг - первый ли это персептрон вообще, если нет то можно удалять первую пару, т.к. на вход и так будет даваться выход от первого персептрона и может не нужен нам дополнительный вес или инпут.
   {

        //свойства для инпута
        public JLabel jl_input;
        public JTextField jtf_input;

        //свойства для весов
        public JLabel jl_weight;
        public JTextField jtf_weight;

        public boolean is_first;
        public boolean is_in_second_perceptron;

       public Two_comps(JLabel jl_input, JTextField jtf_input, JLabel jl_weight, JTextField jtf_weight, boolean is_first, boolean is_in_second_perceptron) {
           this.jl_input = jl_input;
           this.jtf_input = jtf_input;
           this.jl_weight = jl_weight;
           this.jtf_weight = jtf_weight;
           this.is_first = is_first;
           this.is_in_second_perceptron = is_in_second_perceptron;
       }



   }

    static class Add_button_pressed extends AbstractAction
    {
        public Perceptron p;
        public Add_button_pressed(Perceptron p) {
            this.p = p;

        }

        @Override
        public void actionPerformed(ActionEvent e) {

            boolean is_first = false;
            boolean is_in_second_perceptron= false;
            is_first = p.jfc_compt_list.size()<=0 ? true :false;
            is_in_second_perceptron = !p.is_first;


            Two_comps  temp_two_comp=  p.add_row_of_inputs(p.jfc_compt_list.size(), is_first, is_in_second_perceptron); // Создаем объект Two_comps
            p.jfc_compt_list.add(temp_two_comp);                                                                        // Добавляем его в лист  jfc_compt_list
            JPanel temp_jpanel = p.add_two_row_of_inputs_to_the_panels_2(temp_two_comp);                                // создаем панельку из объекта Two_comps
            p.rows_of_input_jpanels.add(temp_jpanel);                                                                   //Добавляем в лист rows_of_input_jpanels
            p.inputs_jf.add(temp_jpanel);                                                                               //Добавляем новую панельку на inputs_jf
            System.out.println("is_first:= " + is_first + " is_in_second_perceptron:= " + is_in_second_perceptron);     //Дебаг текст
            p.del_x_btn.setEnabled(true);
            p.link_for_this_palne.validate();                                                                           //Перерисовываем главную панельку
            p.link_for_this_palne.repaint();
        }
    }


    static class Del_button_pressed extends AbstractAction
    {
        public Perceptron p;
        public Del_button_pressed(Perceptron p) {
            this.p = p;

        }

        @Override
        public void actionPerformed(ActionEvent e) {

            boolean is_first = false;
            boolean is_in_second_perceptron= false;
            int num = p.rows_of_input_jpanels.size();
            int last_elem = num-1;

            p.inputs_jf.remove(last_elem);
            p.rows_of_input_jpanels.remove(last_elem);
            p.jfc_compt_list.remove(last_elem);

            if (p.rows_of_input_jpanels.size()-1 <=0)
            {
                p.del_x_btn.setEnabled(false);
            }

            p.link_for_this_palne.validate();                                                                           //Перерисовываем главную панельку
            p.link_for_this_palne.repaint();



        }
    }





    static class Test_Button_action_add_new_button extends AbstractAction
    {
        public Perceptron p;
        public Test_Button_action_add_new_button(Perceptron p) {
            this.p = p;

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            //JOptionPane.showMessageDialog(null, "dssds", "InfoBox: " + "sdsd", JOptionPane.INFORMATION_MESSAGE);
            p.Add_button_function();

        }
    }




}
