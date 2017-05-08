/*
 * Copyright (C) 2013 by SUTD (Singapore)
 * All rights reserved.
 *
 * 	Author: SUTD
 *  Version:  $Revision: 1 $
 */

package cfgcoverage.jacoco.utils;

import org.objectweb.asm.Opcodes;

/**
 * @author LLT
 *
 */
public final class OpcodeUtils {
	private OpcodeUtils(){}
	
	private static final String[] OPCODES = new String[202];
	static {
		OPCODES[0] = "NOP";
		OPCODES[1] = "ACONST_NULL";
		OPCODES[2] = "ICONST_M1";
		OPCODES[3] = "ICONST_0";
		OPCODES[4] = "ICONST_1";
		OPCODES[5] = "ICONST_2";
		OPCODES[6] = "ICONST_3";
		OPCODES[7] = "ICONST_4";
		OPCODES[8] = "ICONST_5";
		OPCODES[9] = "LCONST_0";
		OPCODES[10] = "LCONST_1";
		OPCODES[11] = "FCONST_0";
		OPCODES[12] = "FCONST_1";
		OPCODES[13] = "FCONST_2";
		OPCODES[14] = "DCONST_0";
		OPCODES[15] = "DCONST_1";
		OPCODES[16] = "BIPUSH";
		OPCODES[17] = "SIPUSH";
		OPCODES[18] = "LDC";
		OPCODES[19] = "LDC_W";
		OPCODES[20] = "LDC2_W";
		OPCODES[21] = "ILOAD";
		OPCODES[22] = "LLOAD";
		OPCODES[23] = "FLOAD";
		OPCODES[24] = "DLOAD";
		OPCODES[25] = "ALOAD";
		OPCODES[26] = "ILOAD_0";
		OPCODES[27] = "ILOAD_1";
		OPCODES[28] = "ILOAD_2";
		OPCODES[29] = "ILOAD_3";
		OPCODES[30] = "LLOAD_0";
		OPCODES[31] = "LLOAD_1";
		OPCODES[32] = "LLOAD_2";
		OPCODES[33] = "LLOAD_3";
		OPCODES[34] = "FLOAD_0";
		OPCODES[35] = "FLOAD_1";
		OPCODES[36] = "FLOAD_2";
		OPCODES[37] = "FLOAD_3";
		OPCODES[38] = "DLOAD_0";
		OPCODES[39] = "DLOAD_1";
		OPCODES[40] = "DLOAD_2";
		OPCODES[41] = "DLOAD_3";
		OPCODES[42] = "ALOAD_0";
		OPCODES[43] = "ALOAD_1";
		OPCODES[44] = "ALOAD_2";
		OPCODES[45] = "ALOAD_3";
		OPCODES[46] = "IALOAD";
		OPCODES[47] = "LALOAD";
		OPCODES[48] = "FALOAD";
		OPCODES[49] = "DALOAD";
		OPCODES[50] = "AALOAD";
		OPCODES[51] = "BALOAD";
		OPCODES[52] = "CALOAD";
		OPCODES[53] = "SALOAD";
		OPCODES[54] = "ISTORE";
		OPCODES[55] = "LSTORE";
		OPCODES[56] = "FSTORE";
		OPCODES[57] = "DSTORE";
		OPCODES[58] = "ASTORE";
		OPCODES[59] = "ISTORE_0";
		OPCODES[60] = "ISTORE_1";
		OPCODES[61] = "ISTORE_2";
		OPCODES[62] = "ISTORE_3";
		OPCODES[63] = "LSTORE_0";
		OPCODES[64] = "LSTORE_1";
		OPCODES[65] = "LSTORE_2";
		OPCODES[66] = "LSTORE_3";
		OPCODES[67] = "FSTORE_0";
		OPCODES[68] = "FSTORE_1";
		OPCODES[69] = "FSTORE_2";
		OPCODES[70] = "FSTORE_3";
		OPCODES[71] = "DSTORE_0";
		OPCODES[72] = "DSTORE_1";
		OPCODES[73] = "DSTORE_2";
		OPCODES[74] = "DSTORE_3";
		OPCODES[75] = "ASTORE_0";
		OPCODES[76] = "ASTORE_1";
		OPCODES[77] = "ASTORE_2";
		OPCODES[78] = "ASTORE_3";
		OPCODES[79] = "IASTORE";
		OPCODES[80] = "LASTORE";
		OPCODES[81] = "FASTORE";
		OPCODES[82] = "DASTORE";
		OPCODES[83] = "AASTORE";
		OPCODES[84] = "BASTORE";
		OPCODES[85] = "CASTORE";
		OPCODES[86] = "SASTORE";
		OPCODES[87] = "POP";
		OPCODES[88] = "POP2";
		OPCODES[89] = "DUP";
		OPCODES[90] = "DUP_X1";
		OPCODES[91] = "DUP_X2";
		OPCODES[92] = "DUP2";
		OPCODES[93] = "DUP2_X1";
		OPCODES[94] = "DUP2_X2";
		OPCODES[95] = "SWAP";
		OPCODES[96] = "IADD";
		OPCODES[97] = "LADD";
		OPCODES[98] = "FADD";
		OPCODES[99] = "DADD";
		OPCODES[100] = "ISUB";
		OPCODES[101] = "LSUB";
		OPCODES[102] = "FSUB";
		OPCODES[103] = "DSUB";
		OPCODES[104] = "IMUL";
		OPCODES[105] = "LMUL";
		OPCODES[106] = "FMUL";
		OPCODES[107] = "DMUL";
		OPCODES[108] = "IDIV";
		OPCODES[109] = "LDIV";
		OPCODES[110] = "FDIV";
		OPCODES[111] = "DDIV";
		OPCODES[112] = "IREM";
		OPCODES[113] = "LREM";
		OPCODES[114] = "FREM";
		OPCODES[115] = "DREM";
		OPCODES[116] = "INEG";
		OPCODES[117] = "LNEG";
		OPCODES[118] = "FNEG";
		OPCODES[119] = "DNEG";
		OPCODES[120] = "ISHL";
		OPCODES[121] = "LSHL";
		OPCODES[122] = "ISHR";
		OPCODES[123] = "LSHR";
		OPCODES[124] = "IUSHR";
		OPCODES[125] = "LUSHR";
		OPCODES[126] = "IAND";
		OPCODES[127] = "LAND";
		OPCODES[128] = "IOR";
		OPCODES[129] = "LOR";
		OPCODES[130] = "IXOR";
		OPCODES[131] = "LXOR";
		OPCODES[132] = "IINC";
		OPCODES[133] = "I2L";
		OPCODES[134] = "I2F";
		OPCODES[135] = "I2D";
		OPCODES[136] = "L2I";
		OPCODES[137] = "L2F";
		OPCODES[138] = "L2D";
		OPCODES[139] = "F2I";
		OPCODES[140] = "F2L";
		OPCODES[141] = "F2D";
		OPCODES[142] = "D2I";
		OPCODES[143] = "D2L";
		OPCODES[144] = "D2F";
		OPCODES[145] = "I2B";
		OPCODES[146] = "I2C";
		OPCODES[147] = "I2S";
		OPCODES[148] = "LCMP";
		OPCODES[149] = "FCMPL";
		OPCODES[150] = "FCMPG";
		OPCODES[151] = "DCMPL";
		OPCODES[152] = "DCMPG";
		OPCODES[153] = "IFEQ";
		OPCODES[154] = "IFNE";
		OPCODES[155] = "IFLT";
		OPCODES[156] = "IFGE";
		OPCODES[157] = "IFGT";
		OPCODES[158] = "IFLE";
		OPCODES[159] = "IF_ICMPEQ";
		OPCODES[160] = "IF_ICMPNE";
		OPCODES[161] = "IF_ICMPLT";
		OPCODES[162] = "IF_ICMPGE";
		OPCODES[163] = "IF_ICMPGT";
		OPCODES[164] = "IF_ICMPLE";
		OPCODES[165] = "IF_ACMPEQ";
		OPCODES[166] = "IF_ACMPNE";
		OPCODES[167] = "GOTO";
		OPCODES[168] = "JSR";
		OPCODES[169] = "RET";
		OPCODES[170] = "TABLESWITCH";
		OPCODES[171] = "LOOKUPSWITCH";
		OPCODES[172] = "IRETURN";
		OPCODES[173] = "LRETURN";
		OPCODES[174] = "FRETURN";
		OPCODES[175] = "DRETURN";
		OPCODES[176] = "ARETURN";
		OPCODES[177] = "RETURN";
		OPCODES[178] = "GETSTATIC";
		OPCODES[179] = "PUTSTATIC";
		OPCODES[180] = "GETFIELD";
		OPCODES[181] = "PUTFIELD";
		OPCODES[182] = "INVOKEVIRTUAL";
		OPCODES[183] = "INVOKESPECIAL";
		OPCODES[184] = "INVOKESTATIC";
		OPCODES[185] = "INVOKEINTERFACE";
		OPCODES[186] = "INVOKEDYNAMIC";
		OPCODES[187] = "NEW";
		OPCODES[188] = "NEWARRAY";
		OPCODES[189] = "ANEWARRAY";
		OPCODES[190] = "ARRAYLENGTH";
		OPCODES[191] = "ATHROW";
		OPCODES[192] = "CHECKCAST";
		OPCODES[193] = "INSTANCEOF";
		OPCODES[194] = "MONITORENTER";
		OPCODES[195] = "MONITOREXIT";
		OPCODES[196] = "WIDE";
		OPCODES[197] = "MULTIANEWARRAY";
		OPCODES[198] = "IFNULL";
		OPCODES[199] = "IFNONNULL";
		OPCODES[200] = "GOTO_W";
		OPCODES[201] = "JSR_W";
	}
	
	public static String getCode(int opcode) {
		return OPCODES[opcode];
	}
	
	public static boolean isLoadInst(int opcode) {
		return (opcode >= Opcodes.ILOAD && opcode <= Opcodes.SALOAD);
	}

	public static boolean isCondition(int opcode) {
		return (opcode >= Opcodes.IFEQ  && opcode <= Opcodes.IF_ACMPNE) || 
				( opcode >= Opcodes.IFNULL && opcode <= Opcodes.IFNONNULL);
	}
}
