package com.sixgodit.xzz.jlatexmathexample;

public class ExampleFormula {
    private static String mExample3 = "${\\frac{电梯上升10米里做的功\\phantom{电梯上升米里做的功}}{电梯上升10米花的时间\\phantom{电梯上升米花的时间}}}$";

    private static String mExample5 = "${\\rm CO_2+4H_2\\;\n" +
            "\\mathop{\\substack{-\\!-\\!-\\!-\\!-\\!\\rightharpoonup \\\\ \\leftharpoondown\\!-\\!-\\!-\\!-\\!-}}^{phantom}_{\\triangle}\\;\n" +
            "CH_4+2H_2O}$";

    private static String mExample1 = "${ \\buildrel{溶解}\\over\\longrightarrow }$黏液${\\rightarrow }$体壁${\\rightarrow }$毛细血管${\\rightarrow }$";

    private static String mExample4 = "${3Fe+2O _{2}   \\mathop{\\substack{=\\!=\\!=\\!=\\!=\\!=}}^{点燃} Fe _{3} O _{4}}$";

    private static String mExample2 = "${a+b\\boldsymbol{i}}$ 和 ${a+b\\pmb{i}}$";
    public static String mExample21 = "${\\% \\Delta price = 100\\times [\\exp (a\\mathord{\\buildrel{\\lower0pt\\hbox{$\\scriptscriptstyle\\frown$}}\\over g} e) - 1] = 100\\times [\\exp ( - 0.008) - 1] =  - 0.8\\%}$";
    private static String mExample6 = "${\\mathop{\\substack{-\\!-\\!-\\!-\\!-\\!\\rightharpoonup \\\\ \\leftharpoondown\\!-\\!-\\!-\\!-\\!-}}^{催化剂}_{\\triangle}}$ ";

    private static String mExample7 = "解：∵${A\\cup B= \\{1,\\, 2\\}}$，则${A}$，${B}$均为${\\{1,\\, 2\\}}$的子集，" +
            "<br />即${A}$，${B\\in \\{\\varnothing ,\\, \\{1\\},\\, \\{2\\},\\, \\{1,\\, 2\\}\\}}$，" +
            "<br />当${A= \\varnothing }$时，${B= \\{1,\\, 2\\}}$，<br />当${A= \\{1\\}}$时，${B= \\{1,\\, 2\\}}$或${B= \\{2\\}}$，<br />当${A= \\{2\\}}$时，${B= \\{1,\\, 2\\}}$或${B= \\{1\\}}$，<br />${A= \\{1,\\, 2\\}}$时，${B= \\{1,\\, 2\\}}$，或${B= \\{1\\}}$，或${B= \\{2\\}}$，或${B= \\varnothing }$，<br />共${9}$种情况，<br />故选：${D}$";
    private static String mExample0 = "${CO _{2} +H _{2} O  \\mathrel{\\mathop{\\kern{0pt}\\longrightarrow}\\limits_{叶绿素}^{日光}}}$";
    private static String mExample03 = "${CuSO _{4} \\cdot 5H _{2} O}$";
    private static String mExample01 ="用${\\rm Pt}$电极电解${\\rm CuSO_{4}}$溶液，发生${\\rm 2CuSO_{4}+ 2H_{2}O \\mathop{\\substack{= \\!= \\!= \\!= \\!= \\!= }}^{电解\\phantom{电解}}2Cu+ O_{2}\\uparrow + 2H_{2}SO_{4}}$，只在阳极产生气体，由阴阳两极产生相同体积的气体，还发生${\\rm 2H_{2}O \\mathop{\\substack{= \\!= \\!= \\!= \\!= \\!= }}^{电解\\phantom{电解}}2H_{2}\\uparrow + O_{2}\\uparrow }$，结合转移的电子数来计算解答．";
    private static String mExample02 = "解：由${\\rm 2H_{2}O \\mathop{\\substack{= \\!= \\!= \\!= \\!= \\!= }}^{电解\\phantom{电解}}2H_{2}\\uparrow + O_{2}\\uparrow \\sim 4e^{-}}$，<br />${\\rm  2 1 4}$<br />${\\rm  0.2mol 0.1mol 0.4mol}$<br />则${\\rm 2CuSO_{4}+ 2H_{2}O \\mathop{\\substack{= \\!= \\!= \\!= \\!= \\!= }}^{电解\\phantom{电解}}2Cu+ O_{2}\\uparrow + 2H_{2}SO_{4}}$，<br />${\\rm  0.1mol 0.2mol}$<br />即当电路中通过${\\rm 0.4mol}$电子时，阴阳两极产生相同体积的气体时，生成${\\rm n(H_{2}SO_{4})= 0.2mol}$，<br />所以${\\rm n(H^{+ })= 0.4mol}$，<br />则${\\rm c(H^{+ })=  \\dfrac{0.4mol}{4L}= 0.1mol/L}$，<br />所以${\\rm pH= 1}$，故选${\\rm D}$．";
    private static String mExample8 = "${Cu  \\mathrel{\\mathop{\\kern{0pt}\\longrightarrow}\\limits_{\\triangle }^{H_{2}SO_{4}(浓)}} CuSO _{4}   \\stackrel{NaOH溶液}\\longrightarrow Cu (OH) _{2}}$";
    private static String[] mFormulaArray = new String[]{mExample0,mExample01,mExample02,mExample03,mExample21,mExample8, mExample1, mExample2, mExample3, mExample4, mExample5, mExample6, mExample7};
//    private static String[] mFormulaArray = new String[]{mExample2};

    public static String[] getFormulaArray() {
        return mFormulaArray;
    }
}
