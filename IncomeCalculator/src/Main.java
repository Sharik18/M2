import java.util.Scanner;

public class Main
{
    private static int minIncome = 200000;//Объявляется переменная для порога минимального дохода
    private static int maxIncome = 900000;//Объявляется переменная для порога максимального дохода

    private static int officeRentCharge = 140000; //переменная с суммой расходов за офис 
    private static int telephonyCharge = 12000;// переменная с суммой расходов за телефон
    private static int internetAccessCharge = 7200;//переменная с суммой расходов за интернет

    private static int assistantSalary = 45000;//переменная с суммой расходов на зарплату ассистента 
    private static int financeManagerSalary = 90000;//зарплата бухгалтера 

    private static double mainTaxPercent = 0.24;//дробная переменнная с налоговой ставкой 
    private static double managerPercent = 0.15;//дробная переменная с вознаграждением для менеджера

    private static double minInvestmentsAmount = 100000;//переменная с минимальной суммой для инвестирования

    public static void main(String[] args)
    {
        while(true)//объявление цикла while - будет выполняться пока условие истино
        {
            System.out.println("Введите сумму доходов компании за месяц " +
                "(от 200 до 900 тысяч рублей): ");//выводит сообщение в консоль
            int income = (new Scanner(System.in)).nextInt();//читает из консоли сумму, которую ввел пользователь

            if(!checkIncomeRange(income)) {//в этом блоке с помощью continue цикл прерывается и начинается сначала, если сумма меньше минимального поргоа (функция описана ниже)
                continue;
            }

            double managerSalary = income * managerPercent;//дробная переменная с зарплатой менеджера: доход*процент менеджера
            double pureIncome = income - managerSalary -
                calculateFixedCharges();//дробная переменная с чистой выручкой: доход - зарплата менеджера - (функция, которая считает все застраты описана ниже)
            double taxAmount = mainTaxPercent * pureIncome;//дробная переменная, коотрая считает налог: процент налога * на чистый доход
            double pureIncomeAfterTax = pureIncome - taxAmount;//переменная чистый доход после вычета налогов 

            boolean canMakeInvestments = pureIncomeAfterTax >=
                minInvestmentsAmount;//переменная типа boolean будет true если чистый доход после вычета налогов будет больше или равен значению в переменной minInvestmentsAmount

            System.out.println("Зарплата менеджера: " + managerSalary); //вывод в сообщении системы зп менеджера в зависимости от суммы
            System.out.println("Общая сумма налогов: " +
                (taxAmount > 0 ? taxAmount : 0));//вывод в сообщении системы суммы налогов через тернарный оператор(?): сумма налогов или 0 в зависимости от суммы дохода
            System.out.println("Общая сумма налогов: " +
            System.out.println("Компания может инвестировать: " +
                (canMakeInvestments ? "да" : "нет"));//вывод заключения о том, может ли компания инвестировать
            if(pureIncome < 0) {
                System.out.println("Бюджет в минусе! Нужно срочно зарабатывать!");
            }//условие проверяет ,если чистый доход меньше 0, то система выводит соответствующее сообщение
        }
    }

    private static boolean checkIncomeRange(int income)//переменная содержит функцию которая проверяет находится ли доход в пределах заданной в переменных границы, по умолчанию true
    {
        if(income < minIncome)//условие проверяет минимальную границу и, если доход мпеньше, то выводит соответствующее сообщение
        {
            System.out.println("Доход меньше нижней границы");//выводит сообщение
            return false;//возвраащет false
        }
        if(income > maxIncome)//если доход больше верхней границы
        {
            System.out.println("Доход выше верхней границы");//выводит сообщение и возвращает false
            return false;
        }
        return true;
    }

    private static int calculateFixedCharges()
    {
        return officeRentCharge +
                telephonyCharge +
                internetAccessCharge +
                assistantSalary +
                financeManagerSalary;
    }
}//здесь путем сложения вычисляются все необходимые расходы компании
