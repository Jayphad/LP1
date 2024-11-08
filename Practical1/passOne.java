import java.io.*;
// import java.util.*;

public class passOne {
	public static void main(String args[]) throws IOException {

		String assembly[][] = {
				{ "", "START", "101", "" },
				{ "", "READ", "N", "" },
				{ "", "MOVER", "BREG", "ONE" },
				{ "", "MOVEM", "BREG", "TERM" },
				{ "AGAIN", "MULT", "BREG", "TERM" },
				{ "", "MOVER", "CREG", "TERM" },
				{ "", "ADD", "CREG", "ONE" },
				{ "N", "DS", "2", "" },
				{ "RESULT", "DS", "2", "" },
				{ "ONE", "DC", "1", "" },
				{ "TERM", "DS", "1", "" },
				{ "END", "", "", "" }
		};
		
		String OpCode[][] = {
				{ "ADD", "IS", "01" },
				{ "MULT", "IS", "02" },
				{ "MOVER", "IS", "03" },
				{ "MOVEM", "IS", "04" },
				{ "READ", "IS", "05" },
				{ "START", "AD", "01" },
				{ "END", "AD", "02" },
				{ "DC", "DL", "01" },
				{ "DS", "DL", "02" }
		};

		String Register[][] = {
				{ "AREG", "1" },
				{ "BREG", "2" },
				{ "CREG", "3" },
				{ "DREG", "4" }
		};

		String symbolTable[][] = new String[5][2];
		int locationCounter = Integer.parseInt(assembly[0][2]);
		int row = 0;
		Operation op = new Operation();
		System.out.println("\n");
		System.out.format("%10s%10s%10s%10s%10s%12s%15s", "LABEL", "OPCODE", "OPERAND1", "OPERAND2", "LC", "IC OPCODE","ICOPERAND1\n");
		for (int i = 1; i < assembly.length - 1; i++) {
			if (assembly[i][0] != "") {
				symbolTable[row][0] = assembly[i][0];
				symbolTable[row][1] = Integer.toString(locationCounter);
				row++;
				if (assembly[i][1] == "DS") {
					
					System.out.format("%10s%10s%10s%10s%10s%10s%15s\n", assembly[i][0], assembly[i][1], assembly[i][2],
							assembly[i][3], locationCounter, op.IC_OPCODE(assembly[i][1], OpCode),op.IC_OPERAND_1(assembly[i][2], Register));
					locationCounter += Integer.parseInt(assembly[i][2]);
				} else {
					
					System.out.format("%10s%10s%10s%10s%10s%10s%15s\n", assembly[i][0], assembly[i][1], assembly[i][2],
							assembly[i][3], locationCounter, op.IC_OPCODE(assembly[i][1], OpCode),op.IC_OPERAND_1(assembly[i][2], Register));
					locationCounter++;
				}

			} else {
				System.out.format("%10s%10s%10s%10s%10s%10s%15s\n", assembly[i][0], assembly[i][1], assembly[i][2],
						assembly[i][3], locationCounter, op.IC_OPCODE(assembly[i][1], OpCode),op.IC_OPERAND_1(assembly[i][2], Register));
				locationCounter++;
			}
		}

		//show symbol table
		op.DispST(symbolTable);

	}

}

 class Operation{
	public String IC_OPCODE(String str, String OpCode[][]) {
		for (int i = 0; i < OpCode.length; i++) {
			if (str.equals(OpCode[i][0])) {
				String result = "(" + OpCode[i][1] + "," + OpCode[i][2] + ")";
				return result;
			}
		}
		return "";
	}
	public String IC_OPERAND_1(String str, String Register[][]) {
		for (int i = 0; i < Register.length; i++) {
			if (str.equals(Register[i][0])) {
				String result = "(" + Register[i][0] + "," + Register[i][1] + ")";
				return result;
			}
		}
		return "";
	}
	public void DispST(String arr[][]){
		System.out.println("\n\nSymbol Table :");
		for(int i=0;i<arr.length;i++){
			System.out.println(arr[i][0]+"\t"+arr[i][1]);
		}

	}
}
