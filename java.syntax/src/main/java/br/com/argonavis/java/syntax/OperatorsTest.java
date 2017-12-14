package br.com.argonavis.java.syntax;

import java.text.DecimalFormat;

public class OperatorsTest {

	public static void main(String[] args) {
		// Arithmetic
		byte a = 5 + 6;
		short b = 5 * 6;
		int c = a / b;
		long d = c % 5;
		float e = d - 9.0f;
		double f = 1.0 / 20000;
		char g = 'A' + 3;
		
		System.out.println("byte a = 5 + 6: " + a);
		System.out.println("short b = 5 * 6: " + b);
		System.out.println("int c = a / b: " + c);
		System.out.println("long d = c % 5: " + d);
		System.out.println("float e = d - 9.0f: " + e);
		System.out.println("double f = 1.0 / 20000: " + f);
		System.out.println("char g = 'A' + 3: " + g);
		
		// Decrement and Increment, Unary
		System.out.println();
		System.out.println("a: " + a);
		
		byte y = ++a;
		byte x = a++;
		int z = -x;

		System.out.println("byte y = ++a: " + y);
		System.out.println("byte x = a++: " + x);
		System.out.println("a: " + a);
		System.out.println("int z = -x: " + z);
		
		// Integer literals (all byte will be promoted to int when in binary expression)
		byte one   = 1;     // decimal notation
		byte seven = 07;    // octal notation = 7
		byte ten = 0b1010;  // binary notation = 10
		byte fifteen = 0xf; // hex notation = 15
		byte twenty = 20;          // 20
		byte twentyfour = 030;     // 24
		byte twentyfive = 0x19;    // 25
		byte thirtyone = 0b11111;  // 31
		
		System.out.println();
		System.out.println(" Binary  | Octal  | Decimal | Hex ");
		System.out.println(" 0b" + Integer.toBinaryString(one) + "\t |   0" + Integer.toOctalString(one) + "   |    " + one + "    | 0x" + Integer.toHexString(one));
		System.out.println(" 0b" + Integer.toBinaryString(seven) + "\t |   0" + Integer.toOctalString(seven) + "   |    " + seven + "    | 0x" + Integer.toHexString(seven));
		System.out.println(" 0b" + Integer.toBinaryString(ten) + "\t |   0" + Integer.toOctalString(ten) + "  |    " + ten + "   | 0x" + Integer.toHexString(ten));
		System.out.println(" 0b" + Integer.toBinaryString(fifteen) + "\t |   0" + Integer.toOctalString(fifteen) + "  |    " + fifteen + "   | 0x" + Integer.toHexString(fifteen));
		System.out.println(" 0b" + Integer.toBinaryString(twenty) + " |   0" + Integer.toOctalString(twenty) + "  |    " + twenty + "   | 0x" + Integer.toHexString(twenty));
		System.out.println(" 0b" + Integer.toBinaryString(twentyfour) + " |   0" + Integer.toOctalString(twentyfour) + "  |    " + twentyfour + "   | 0x" + Integer.toHexString(twentyfour));
		System.out.println(" 0b" + Integer.toBinaryString(twentyfive) + " |   0" + Integer.toOctalString(twentyfive) + "  |    " + twentyfive + "   | 0x" + Integer.toHexString(twentyfive));
		System.out.println(" 0b" + Integer.toBinaryString(thirtyone) + " |   0" + Integer.toOctalString(thirtyone) + "  |    " + thirtyone + "   | 0x" + Integer.toHexString(thirtyone));
		
		// Shift left
		int t1 = ten << 1;
		int t2 = ten << 2;
		int t3 = ten << 3;
		int t4 = ten << 4;
		int t8 = ten << 8;
		int t16 = ten << 16;
		int t27 = ten << 27;
		int t28 = ten << 28; // overflow
		int t29 = ten << 29; // overflow
		
		System.out.println();
		System.out.println("1010:       " + String.format("%32s", Integer.toBinaryString(ten)).replace(' ', '0') + ", 0" + Integer.toOctalString(ten) + ", " + ten + ", 0x" + Integer.toHexString(ten).toUpperCase());
		System.out.println("1010 << 1:  " + String.format("%32s", Integer.toBinaryString(t1)).replace(' ', '0') + ", 0" + Integer.toOctalString(t1) + ", " + t1 + ", 0x" + Integer.toHexString(t1).toUpperCase());
		System.out.println("1010 << 2:  " + String.format("%32s", Integer.toBinaryString(t2)).replace(' ', '0') + ", 0" + Integer.toOctalString(t2) + ", " + t2 + ", 0x" + Integer.toHexString(t2).toUpperCase());
		System.out.println("1010 << 3:  " + String.format("%32s", Integer.toBinaryString(t3)).replace(' ', '0') + ", 0" + Integer.toOctalString(t3) + ", " + t3 + ", 0x" + Integer.toHexString(t3).toUpperCase());
		System.out.println("1010 << 4:  " + String.format("%32s", Integer.toBinaryString(t4)).replace(' ', '0') + ", 0" + Integer.toOctalString(t4) + ", " + t4 + ", 0x" + Integer.toHexString(t4).toUpperCase());
		System.out.println("1010 << 8:  " + String.format("%32s", Integer.toBinaryString(t8)).replace(' ', '0') + ", 0" + Integer.toOctalString(t8) + ", " + t8 + ", 0x" + Integer.toHexString(t8).toUpperCase());
		System.out.println("1010 << 16: " + String.format("%32s", Integer.toBinaryString(t16)).replace(' ', '0') + ", 0" + Integer.toOctalString(t16) + ", " + t16 + ", 0x" + Integer.toHexString(t16).toUpperCase());
		System.out.println("1010 << 27: " + String.format("%32s", Integer.toBinaryString(t27)).replace(' ', '0') + ", 0" + Integer.toOctalString(t27) + ", " + t27 + ", 0x" + Integer.toHexString(t27).toUpperCase());
		System.out.println("1010 << 28: " + String.format("%32s", Integer.toBinaryString(t28)).replace(' ', '0') + ", 0" + Integer.toOctalString(t28) + ", " + t28 + ", 0x" + Integer.toHexString(t28).toUpperCase());
		System.out.println("1010 << 29: " + String.format("%32s", Integer.toBinaryString(t29)).replace(' ', '0') + ", 0" + Integer.toOctalString(t29) + ", " + t29 + ", 0x" + Integer.toHexString(t29).toUpperCase());

		// Shift right
		int u1 = twentyfour >> 1;
		int u2 = twentyfour >> 2;
		int u3 = twentyfour >> 3;
		int u4 = twentyfour >> 4;
		int u5 = twentyfour >> 5;
		int u6 = twentyfour >> 6;
		int nu1 = -twentyfour >> 1;
		int nu2 = -twentyfour >> 2;
		int nu3 = -twentyfour >> 3;
		int nu4 = -twentyfour >> 4;
		int nu5 = -twentyfour >> 5;
		int nu6 = -twentyfour >> 6;
		
		System.out.println();
		System.out.println("11000:      " + String.format("%32s", Integer.toBinaryString(twentyfour)).replace(' ', '0') + ", 0" + Integer.toOctalString(twentyfour) + ", " + twentyfour + ", 0x" + Integer.toHexString(twentyfour).toUpperCase());
		System.out.println("11000 >> 1: " + String.format("%32s", Integer.toBinaryString(u1)).replace(' ', '0') + ", 0" + Integer.toOctalString(u1) + ", " + u1 + ", 0x" + Integer.toHexString(u1).toUpperCase());
		System.out.println("11000 >> 2: " + String.format("%32s", Integer.toBinaryString(u2)).replace(' ', '0') + ", 0" + Integer.toOctalString(u2) + ",   " + u2 + ", 0x" + Integer.toHexString(u2).toUpperCase());
		System.out.println("11000 >> 3: " + String.format("%32s", Integer.toBinaryString(u3)).replace(' ', '0') + ", 0" + Integer.toOctalString(u3) + ",   " + u3 + ", 0x" + Integer.toHexString(u3).toUpperCase());
		System.out.println("11000 >> 4: " + String.format("%32s", Integer.toBinaryString(u4)).replace(' ', '0') + ", 0" + Integer.toOctalString(u4) + ",   " + u4 + ", 0x" + Integer.toHexString(u4).toUpperCase());
		System.out.println("11000 >> 5: " + String.format("%32s", Integer.toBinaryString(u5)).replace(' ', '0') + ", 0" + Integer.toOctalString(u5) + ",   " + u5 + ", 0x" + Integer.toHexString(u5).toUpperCase());
		System.out.println("11000 >> 6: " + String.format("%32s", Integer.toBinaryString(u6)).replace(' ', '0') + ", 0" + Integer.toOctalString(u6) + ",   " + u6 + ", 0x" + Integer.toHexString(u6).toUpperCase());
		
		System.out.println("1...01000:      " + String.format("%32s", Integer.toBinaryString(-twentyfour)).replace(' ', '0') + ", 0" + Integer.toOctalString(-twentyfour) + ", " + -twentyfour + ", 0x" + Integer.toHexString(-twentyfour).toUpperCase());
		System.out.println("1...01000 >> 1: " + String.format("%32s", Integer.toBinaryString(nu1)).replace(' ', '0') + ", 0" + Integer.toOctalString(nu1) + ", " + nu1 + ", 0x" + Integer.toHexString(nu1).toUpperCase());
		System.out.println("1...01000 >> 2: " + String.format("%32s", Integer.toBinaryString(nu2)).replace(' ', '0') + ", 0" + Integer.toOctalString(nu2) + ",  " + nu2 + ", 0x" + Integer.toHexString(nu2).toUpperCase());
		System.out.println("1...01000 >> 3: " + String.format("%32s", Integer.toBinaryString(nu3)).replace(' ', '0') + ", 0" + Integer.toOctalString(nu3) + ",  " + nu3 + ", 0x" + Integer.toHexString(nu3).toUpperCase());
		System.out.println("1...01000 >> 4: " + String.format("%32s", Integer.toBinaryString(nu4)).replace(' ', '0') + ", 0" + Integer.toOctalString(nu4) + ",  " + nu4 + ", 0x" + Integer.toHexString(nu4).toUpperCase());
		System.out.println("1...01000 >> 5: " + String.format("%32s", Integer.toBinaryString(nu5)).replace(' ', '0') + ", 0" + Integer.toOctalString(nu5) + ",  " + nu5 + ", 0x" + Integer.toHexString(nu5).toUpperCase());
		System.out.println("1...01000 >> 6: " + String.format("%32s", Integer.toBinaryString(nu6)).replace(' ', '0') + ", 0" + Integer.toOctalString(nu6) + ",  " + nu6 + ", 0x" + Integer.toHexString(nu6).toUpperCase());

		// Unsigned shift right
		int us1 = twentyfour >>> 1;
		int us2 = twentyfour >>> 2;
		int us3 = twentyfour >>> 3;
		int us4 = twentyfour >>> 4;
		int us5 = twentyfour >>> 5;
		int us6 = twentyfour >>> 6;
		int nus1 = -twentyfour >>> 1;
		int nus2 = -twentyfour >>> 2;
		int nus3 = -twentyfour >>> 3;
		int nus4 = -twentyfour >>> 4;
		int nus5 = -twentyfour >>> 5;
		int nus6 = -twentyfour >>> 6;
		
		System.out.println();
		System.out.println("11000:      " + String.format("%32s", Integer.toBinaryString(twentyfour)).replace(' ', '0') + ", 0" + Integer.toOctalString(twentyfour) + ", " + twentyfour + ", 0x" + Integer.toHexString(twentyfour).toUpperCase());
		System.out.println("11000 >>> 1: " + String.format("%32s", Integer.toBinaryString(us1)).replace(' ', '0') + ", 0" + Integer.toOctalString(us1) + ", " + us1 + ", 0x" + Integer.toHexString(us1).toUpperCase());
		System.out.println("11000 >>> 2: " + String.format("%32s", Integer.toBinaryString(us2)).replace(' ', '0') + ", 0" + Integer.toOctalString(us2) + ",   " + us2 + ", 0x" + Integer.toHexString(us2).toUpperCase());
		System.out.println("11000 >>> 3: " + String.format("%32s", Integer.toBinaryString(us3)).replace(' ', '0') + ", 0" + Integer.toOctalString(us3) + ",   " + us3 + ", 0x" + Integer.toHexString(us3).toUpperCase());
		System.out.println("11000 >>> 4: " + String.format("%32s", Integer.toBinaryString(us4)).replace(' ', '0') + ", 0" + Integer.toOctalString(us4) + ",   " + us4 + ", 0x" + Integer.toHexString(us4).toUpperCase());
		System.out.println("11000 >>> 5: " + String.format("%32s", Integer.toBinaryString(us5)).replace(' ', '0') + ", 0" + Integer.toOctalString(us5) + ",   " + us5 + ", 0x" + Integer.toHexString(us5).toUpperCase());
		System.out.println("11000 >>> 6: " + String.format("%32s", Integer.toBinaryString(us6)).replace(' ', '0') + ", 0" + Integer.toOctalString(us6) + ",   " + us6 + ", 0x" + Integer.toHexString(us6).toUpperCase());
		
		System.out.println("1...01000:      " + String.format("%32s", Integer.toBinaryString(-twentyfour)).replace(' ', '0') + ", 0" + Integer.toOctalString(-twentyfour) + ", " + -twentyfour + ", 0x" + Integer.toHexString(-twentyfour).toUpperCase());
		System.out.println("1...01000 >>> 1: " + String.format("%32s", Integer.toBinaryString(nus1)).replace(' ', '0') + ", 0" + Integer.toOctalString(nus1) + ", " + nus1 + ", 0x" + Integer.toHexString(nus1).toUpperCase());
		System.out.println("1...01000 >>> 2: " + String.format("%32s", Integer.toBinaryString(nus2)).replace(' ', '0') + ", 0" + Integer.toOctalString(nus2) + ",  " + nus2 + ", 0x" + Integer.toHexString(nus2).toUpperCase());
		System.out.println("1...01000 >>> 3: " + String.format("%32s", Integer.toBinaryString(nus3)).replace(' ', '0') + ", 0" + Integer.toOctalString(nus3) + ",  " + nus3 + ", 0x" + Integer.toHexString(nus3).toUpperCase());
		System.out.println("1...01000 >>> 4: " + String.format("%32s", Integer.toBinaryString(nus4)).replace(' ', '0') + ", 0" + Integer.toOctalString(nus4) + ",  " + nus4 + ", 0x" + Integer.toHexString(nus4).toUpperCase());
		System.out.println("1...01000 >>> 5: " + String.format("%32s", Integer.toBinaryString(nus5)).replace(' ', '0') + ", 0" + Integer.toOctalString(nus5) + ",  " + nus5 + ", 0x" + Integer.toHexString(nus5).toUpperCase());
		System.out.println("1...01000 >>> 6: " + String.format("%32s", Integer.toBinaryString(nus6)).replace(' ', '0') + ", 0" + Integer.toOctalString(nus6) + ",  " + nus6 + ", 0x" + Integer.toHexString(nus6).toUpperCase());

		// Bitwise operators
		int opand = 9 & 5;
		int opor  = 9 | 5;
		int opnot = ~opor;
		int opxor = 9 ^ 5;
		
		System.out.println();
		System.out.println("1001:   " + String.format("%32s", Integer.toBinaryString(9)).replace(' ', '0') + ", 0" + Integer.toOctalString(9) + ", " + 9 + ", 0x" + Integer.toHexString(9).toUpperCase());
		System.out.println("101:    " + String.format("%32s", Integer.toBinaryString(5)).replace(' ', '0') + ", 0" + Integer.toOctalString(5) + ", " + 5 + ", 0x" + Integer.toHexString(5).toUpperCase());
		
		System.out.println("9 & 5:  " + String.format("%32s", Integer.toBinaryString(opand)).replace(' ', '0') + ", 0" + Integer.toOctalString(opand) + ", " + opand + ", 0x" + Integer.toHexString(opand).toUpperCase());
		System.out.println("9 | 5:  " + String.format("%32s", Integer.toBinaryString(opor)).replace(' ', '0') + ", 0" + Integer.toOctalString(opor) + ",   " + opor + ", 0x" + Integer.toHexString(opor).toUpperCase());
		System.out.println("~(9|5): " + String.format("%32s", Integer.toBinaryString(opnot)).replace(' ', '0') + ", 0" + Integer.toOctalString(opnot) + ",   " + opnot + ", 0x" + Integer.toHexString(opnot).toUpperCase());
		System.out.println("9 ^ 5:  " + String.format("%32s", Integer.toBinaryString(opxor)).replace(' ', '0') + ", 0" + Integer.toOctalString(opxor) + ",   " + opxor + ", 0x" + Integer.toHexString(opxor).toUpperCase());

		// Relational
		
		boolean larger = ten > 5;
		boolean largerEqual = ten >= 5;
		boolean less   = ten < 10;
		boolean lessEqual = ten <= ten;
		boolean diff   = 10 != 10;
		boolean equal  = seven == 7;
		
		System.out.println();
		System.out.println("ten > 5: " + larger);
		System.out.println("ten >= 5: " + largerEqual);
		System.out.println("ten < 10: " + less);
		System.out.println("ten <= ten: " + lessEqual);
		System.out.println("10 != 10: " + diff);
		System.out.println("seven == 7: " + equal);
		
		// Boolean (short-circuit)
		
		boolean bor  = larger || diff;
		boolean band = larger && diff;
		boolean bnot = !band;
		
		System.out.println();
		System.out.println("(ten > 5) || (10 != 10): " + bor);
		System.out.println("(ten > 5)  && (10 != 10): " + band);
		System.out.println("!((ten > 5)  && (10 != 10)): " + bnot);
		
	}

}
