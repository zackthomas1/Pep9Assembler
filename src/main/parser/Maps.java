package main.parser;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class Maps {
    
    public static final Map<String, Mnemon> unaryMnemonTable; 
    public static final Map<String, Mnemon> nonunaryMnemonTable; 
    public static final Map<Mnemon, String> mnemonStringTable; 

    static{
        unaryMnemonTable = new HashMap<>();
        unaryMnemonTable.put("asla", Mnemon.M_ASLA);
        unaryMnemonTable.put("asra", Mnemon.M_ASRA);
        unaryMnemonTable.put("stop", Mnemon.M_STOP);

        unaryMnemonTable.put("block", Mnemon.M_BLOCK);  //dot commands
        unaryMnemonTable.put("end", Mnemon.M_END); 

        nonunaryMnemonTable = new HashMap<>();
        nonunaryMnemonTable.put("br", Mnemon.M_BR);     // single-argument
        nonunaryMnemonTable.put("brlt", Mnemon.M_BRLT);
        nonunaryMnemonTable.put("breq", Mnemon.M_BREQ);
        nonunaryMnemonTable.put("brle", Mnemon.M_BRLE);
        nonunaryMnemonTable.put("call", Mnemon.M_CALL);
        
        nonunaryMnemonTable.put("cpwa", Mnemon.M_CPWA); // double-argument
        nonunaryMnemonTable.put("deci", Mnemon.M_DECI);
        nonunaryMnemonTable.put("deco", Mnemon.M_DECO);
        nonunaryMnemonTable.put("adda", Mnemon.M_ADDA);
        nonunaryMnemonTable.put("suba", Mnemon.M_SUBA);
        nonunaryMnemonTable.put("stwa", Mnemon.M_STWA);
        nonunaryMnemonTable.put("ldwa", Mnemon.M_LDWA); 


        mnemonStringTable = new EnumMap<>(Mnemon.class);
        // Unary
        mnemonStringTable.put(Mnemon.M_ASLA, "asla");   
        mnemonStringTable.put(Mnemon.M_ASRA, "asra");
        mnemonStringTable.put(Mnemon.M_STOP, "stop");
        
        mnemonStringTable.put(Mnemon.M_BLOCK, "block"); // dot commans
        mnemonStringTable.put(Mnemon.M_END, "end");
        // Non-Unary
        mnemonStringTable.put(Mnemon.M_BR, "br");       // single-argument
        mnemonStringTable.put(Mnemon.M_BRLT, "brlt");
        mnemonStringTable.put(Mnemon.M_BREQ, "breq");
        mnemonStringTable.put(Mnemon.M_BRLE, "brle");
        mnemonStringTable.put(Mnemon.M_CALL, "call");

        mnemonStringTable.put(Mnemon.M_CPWA, "cpwa");   // double-argument
        mnemonStringTable.put(Mnemon.M_DECI, "deci");
        mnemonStringTable.put(Mnemon.M_DECO, "deco");
        mnemonStringTable.put(Mnemon.M_ADDA, "adda");
        mnemonStringTable.put(Mnemon.M_SUBA, "suba");
        mnemonStringTable.put(Mnemon.M_STWA, "stwa");
        mnemonStringTable.put(Mnemon.M_LDWA, "ldwa");   
    }

}
