package Parser;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class Maps {
    
    public static final Map<String, Mnemon> unaryMnemonTable; 
    public static final Map<String, Mnemon> nonunaryMnemonTable; 
    public static final Map<Mnemon, String> mnemonStringTable; 

    static{
        unaryMnemonTable = new HashMap<>();
        unaryMnemonTable.put("ret", Mnemon.M_RET);
        unaryMnemonTable.put("stop", Mnemon.M_STOP);
        unaryMnemonTable.put("end", Mnemon.M_END); 

        nonunaryMnemonTable = new HashMap<>();
        nonunaryMnemonTable.put("ldwa", Mnemon.M_LDWA); 
        nonunaryMnemonTable.put("neg", Mnemon.M_NEG); 
        nonunaryMnemonTable.put("abs", Mnemon.M_ABS); 
        nonunaryMnemonTable.put("add", Mnemon.M_ADD); 
        nonunaryMnemonTable.put("sub", Mnemon.M_SUB); 
        nonunaryMnemonTable.put("mul", Mnemon.M_MUL); 
        nonunaryMnemonTable.put("div", Mnemon.M_DIV); 
        nonunaryMnemonTable.put("set", Mnemon.M_SET); 

        mnemonStringTable = new EnumMap<>(Mnemon.class);
        // Non-Unary
        mnemonStringTable.put(Mnemon.M_LDWA, "ldwa");
        mnemonStringTable.put(Mnemon.M_NEG, "neg");
        mnemonStringTable.put(Mnemon.M_ABS, "abs");
        mnemonStringTable.put(Mnemon.M_ADD, "add");
        mnemonStringTable.put(Mnemon.M_SUB, "sub");
        mnemonStringTable.put(Mnemon.M_MUL, "mul");
        mnemonStringTable.put(Mnemon.M_DIV, "div");
        mnemonStringTable.put(Mnemon.M_SET, "set");
        // Unary
        mnemonStringTable.put(Mnemon.M_RET, "ret"); 
        mnemonStringTable.put(Mnemon.M_STOP, "stop");
        mnemonStringTable.put(Mnemon.M_END, "end");
    }

}
