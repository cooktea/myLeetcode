import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Author  :   ChenKang
 * Time    :   2019/7/24
 * Info    :    解析布尔表达式
 */

public class Solution_1106 {
    public static void main(String[] args) {
        Solution_1106 s = new Solution_1106();
        System.out.println(s.parseBoolExpr("|(&(t,f,t),!(t))"));
    }

    public boolean parseBoolExpr(String expression) {
        return parse(expression);
    }

    /**
     * 获得布尔表达式的值
     * @param expression    布尔表达式
     * @return  该布尔表达式的逻辑值
     */
    public boolean parse(String expression){
        /*
        如果布尔表达式是t或者f
         */
        if (expression.length() == 1){
            if (expression.equals("t")){
                return true;
            }
            if (expression.equals("f")){
                return false;
            }
        }
        char type = getType(expression);    //获得布尔操作的种类
        expression = removeBrackets(expression);    //移除布尔表达式的最外侧括号和第一个字母
        List<String> expressions = getSubExpression(expression);    //获得子布尔表达式集合
        if (type == '&'){   //与操作
            return and(expressions);
        }
        else if (type == '|'){  //或操作
            return or(expressions);
        }
        else {  //非操作
            return not(expressions);
        }
    }

    /**
     * 移除最外侧括号和第一个字母
     * &(expression1,expression2,...) => expression1,expression2,...
     * @param expression    要移除的布尔表达式
     * @return  移除后的布尔表达式
     */
    public String removeBrackets(String expression){
        StringBuilder sb = new StringBuilder(expression);
        sb = sb.deleteCharAt(0);
        sb = sb.deleteCharAt(0);
        sb = sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    /**
     * 获得布尔表达式操作种类
     * @param expression    布尔表达式
     * @return  操作种类
     */
    public char getType(String expression){
        return expression.charAt(0);
    }

    /**
     * 获得子布尔表达式
     * @param expression    removeBrackets后的布尔表达式
     * @return  子布尔表达式集合
     */
    public List<String> getSubExpression(String expression){
        List<String> subExpression = new LinkedList<>();
        char[] chars = expression.toCharArray();
        int count = 0;
        StringBuilder expressionBuilder = new StringBuilder();
        for (int i=0;i<chars.length;i++){
            if (count == 0 && chars[i] == ','){
                subExpression.add(expressionBuilder.toString());
                expressionBuilder = new StringBuilder();
                continue;
            }
            if (chars[i] == '('){
                count++;
            }
            if (chars[i] == ')'){
                count--;
            }
            expressionBuilder.append(chars[i]);
        }
        subExpression.add(expressionBuilder.toString());
        return subExpression;
    }

    /**
     * 与操作
     * @param expressions   布尔表达式集合
     * @return  与操作后的逻辑值
     */
    public boolean and(List<String> expressions){
        for (String expression : expressions){
            if (parse(expression) == false){
                return false;
            }
        }
        return true;
    }

    /**
     * 或操作
     * @param expressions   布尔表达式集合
     * @return  或操作后的逻辑值
     */
    public boolean or(List<String> expressions){
        for (String expression : expressions){
            if (parse(expression) == true){
                return true;
            }
        }
        return false;
    }

    /**
     * 非操作
     * @param expressions   布尔表达式集合
     * @return  非操作后的逻辑值
     */
    public boolean not(List<String> expressions){
        return !parse(expressions.get(0));
    }

}
