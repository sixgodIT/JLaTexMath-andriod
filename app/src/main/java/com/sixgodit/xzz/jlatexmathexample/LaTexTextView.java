package com.sixgodit.xzz.jlatexmathexample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.widget.TextView;

import org.scilab.forge.jlatexmath.core.AjLatexMath;
import org.scilab.forge.jlatexmath.core.Insets;
import org.scilab.forge.jlatexmath.core.TeXConstants;
import org.scilab.forge.jlatexmath.core.TeXFormula;
import org.scilab.forge.jlatexmath.core.TeXIcon;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bolts.Continuation;
import bolts.Task;

public class LaTexTextView extends TextView {
    private Context mContext;
private  static final String LATEXPATTERN="\\$\\{(.+?)\\}\\$";
private  static final String PHANTOMPATTERN="\\\\phantom\\{(.+?)\\}";

    public LaTexTextView(Context context) {
        super(context);
        init(context);
    }

    public LaTexTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LaTexTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
    }

    public void setLinketext(String text) {
        //去除汉字偏移
        text = getPatternText(text);
        //同步画笔颜色，使生成图片与文字夜色一致
        AjLatexMath.setColor(getCurrentTextColor());
        //先加载空白图片占位
        setText(getSpannable(String.valueOf(Html.fromHtml(text))));
        //异步解析公式，然后生成图片
        setTaskSpannableText(text);
    }

    /**
     * 过滤掉所有\\phantom{}的内容，该内容用来偏移文字显示位置
     * @param text
     * @return
     */
    public String getPatternText(String text) {
        //设置正则表达式的各种格式。
        Pattern pattern = Pattern.compile(PHANTOMPATTERN);
        //查找正则表达式的管理类
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {//查看是否复合正则表达式
            //去除里面复合正则表达式
            final String group = matcher.group();
            if (group.startsWith("\\")) {//是一串 LaTexMath公式
                text = text.replace(group, "");
            }
        }
        return text;
    }

    /**
     * 异步解析公式，然后生成图片 ，TeXIcon生成图片需要放在UI线程，子线程中偶尔会出现图片错乱
     *
     * @param text
     */
    private void setTaskSpannableText(final String text) {
        Task.callInBackground(new Callable<ArrayList<LaTeXInfo>>() {
            @Override
            public ArrayList<LaTeXInfo> call() throws Exception {
                return getLaTexInfoList(String.valueOf(Html.fromHtml(text)));
            }
        }).continueWith(new Continuation<ArrayList<LaTeXInfo>, Object>() {
            @Override
            public Object then(Task<ArrayList<LaTeXInfo>> task) throws Exception {
                ArrayList<LaTeXInfo> laTeXInfos = task.getResult();
                if (laTeXInfos == null) {
                    return null;
                }
                SpannableString spannableString = new SpannableString(Html.fromHtml(text));
                for (int i = 0; i < laTeXInfos.size(); i++) {
                    LaTeXInfo laTeXInfo = laTeXInfos.get(i);
                    Bitmap image = BitmapCacheUtil.getInstance().getBitmapFromMemCache(laTeXInfo.getGroup()+getPaint().getTextSize() / getPaint().density);
                    if (image == null) {
                        image = getBitmap(laTeXInfo.getTeXFormula());
                        BitmapCacheUtil.getInstance().addBitmapToMemoryCache(laTeXInfo.getGroup()+getPaint().getTextSize() / getPaint().density, image);
                    }
                    spannableString.setSpan(new VerticalImageSpan(mContext, image), laTeXInfo.getStart(), laTeXInfo.getEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                setText(spannableString);
                return null;
            }
        }, Task.UI_THREAD_EXECUTOR);
    }

    /**
     * 获取spannable文本。
     *
     * @param text
     * @return
     */
    public SpannableString getSpannable(String text) {
        //主要是使用SpannableString类，
        SpannableString spannableString = new SpannableString(text);
        //设置正则表达式的各种格式。
        Pattern pattern = Pattern.compile(LATEXPATTERN);
        //查找正则表达式的管理类
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {//查看是否复合正则表达式
            //去除里面复合正则表达式
            final String group = matcher.group();
            if (group.startsWith("$")) {//是一串 LaTexMath公式
                //先判断缓存中有没有该公式的图片,
                Bitmap image = BitmapCacheUtil.getInstance().getBitmapFromMemCache(group);
                if (image == null) {//如果没有，先加载空白图片
                    image = BitmapCacheUtil.getInstance().getBitmapFromMemCache("10" + "3");
                    if (image == null) {
                        image = getNullBitmap(10, 3);
                        BitmapCacheUtil.getInstance().addBitmapToMemoryCache("10" + "3", image);
                    }
                }
                spannableString.setSpan(new VerticalImageSpan(mContext, image), matcher.start(), matcher.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return spannableString;
    }

    /**
     * 获取公式解析对象集合，该操作费时，需要放在子线程执行
     *
     * @param text
     * @return
     */
    public ArrayList<LaTeXInfo> getLaTexInfoList(String text) {
        //设置正则表达式的各种格式。
        Pattern pattern = Pattern.compile(LATEXPATTERN);
        //查找正则表达式的管理类
        Matcher matcher = pattern.matcher(text);
        ArrayList<LaTeXInfo> mLaTexInfos = new ArrayList<>();
        while (matcher.find()) {//查看是否复合正则表达式
            //去除里面复合正则表达式
            final String group = matcher.group();
            if (group.startsWith("$")) {//是一串 LaTexMath公式
                TeXFormula teXFormula = TeXFormula.getPartialTeXFormula(group);
//                TeXFormula teXFormula = new TeXFormula(group);
                LaTeXInfo laTeXInfo = new LaTeXInfo(teXFormula, matcher.start(), matcher.end(), group);
                mLaTexInfos.add(laTeXInfo);
            }
        }
        return mLaTexInfos;
    }

    /**
     * 根据解析后的对象，生成bitmap 需要放在UI线程，子线程中偶尔会出现图片错乱
     *
     * @param formula
     * @return
     */
    private Bitmap getBitmap(TeXFormula formula) {
        TeXIcon icon = formula.new TeXIconBuilder()
                .setStyle(TeXConstants.STYLE_DISPLAY)
                .setSize(getPaint().getTextSize() / getPaint().density)
                .setWidth(TeXConstants.UNIT_SP, getPaint().getTextSize() / getPaint().density, TeXConstants.ALIGN_LEFT)
                .setIsMaxWidth(true)
                .setInterLineSpacing(TeXConstants.UNIT_SP,
                        AjLatexMath.getLeading(getPaint().getTextSize() / getPaint().density))
                .build();
        icon.setInsets(new Insets(5, 5, 5, 5));
        Bitmap image = Bitmap.createBitmap(icon.getIconWidth(), icon.getIconHeight(),
                Bitmap.Config.ARGB_4444);
        System.out.println(" width=" + icon.getBox().getWidth() + " height=" + icon.getBox().getHeight() +
                " iconwidth=" + icon.getIconWidth() + " iconheight=" + icon.getIconHeight());
        Canvas g2 = new Canvas(image);
        g2.drawColor(Color.TRANSPARENT);
        icon.paintIcon(g2, 0, 0);
        return image;
    }

    /**
     * 根据宽高生成占位图（空白）
     *
     * @param width
     * @param height
     * @return
     */
    private Bitmap getNullBitmap(float width, float height) {
        int w = (int) (width * getPaint().getTextSize() / getPaint().density + 0.99 + 5 + 5);
        int h = (int) (height * getPaint().getTextSize() / getPaint().density + 0.99 + 5 + 5);
        Bitmap image = Bitmap.createBitmap(w, h,
                Bitmap.Config.ARGB_8888);
        Canvas g2 = new Canvas(image);
        g2.drawColor(Color.TRANSPARENT);
        return image;
    }

}
