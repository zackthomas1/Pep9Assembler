package Code;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class Maps {
    
    public static final Map<String, Mnemon> unaryMnemonTable; 
    public static final Map<String, Mnemon> nonunaryMnemonTable; 
    public static final Map<Mnemon, String> mnemonStringTable; 

    static{
        unaryMnemonTable = new HashMap<>();
        unaryMnemonTable.put("stop", Mnemon.M_STOP);
        unaryMnemonTable.put("end", Mnemon.M_END); 

        nonunaryMnemonTable = new HashMap<>();
        nonunaryMnemonTable.put("neg", Mnemon.M_NEG); 
        nonunaryMnemonTable.put("abs", Mnemon.M_ABS); 
        nonunaryMnemonTable.put("add", Mnemon.M_ADD); 
        nonunaryMnemonTable.put("sub", Mnemon.M_SUB); 
        nonunaryMnemonTable.put("mul", Mnemon.M_MUL); 
        nonunaryMnemonTable.put("div", Mnemon.M_DIV); 
        nonunaryMnemonTable.put("set", Mnemon.M_SET); 

        mnemonStringTable = new EnumMap<>(Mnemon.class);
        mnemonStringTable.put(Mnemon.M_NEG, "neg");
        mnemonStringTable.put(Mnemon.M_ABS, "abs");
        mnemonStringTable.put(Mnemon.M_ADD, "add");
        mnemonStringTable.put(Mnemon.M_NEG, "sub");
        mnemonStringTable.put(Mnemon.M_NEG, "mul");
        mnemonStringTable.put(Mnemon.M_NEG, "div");
        mnemonStringTable.put(Mnemon.M_NEG, "set");
        mnemonStringTable.put(Mnemon.M_NEG, "stop");
        mnemonStringTable.put(Mnemon.M_NEG, "end");
    }

}
