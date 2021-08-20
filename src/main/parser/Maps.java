package main.parser;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class Maps {
    
    public static final Map<String, Mnemon> unaryMnemonTable; 
    public static final Map<String, Mnemon> dotCmdMnemonTable;
    public static final Map<String, Mnemon> nonunaryMnemonTable; 

    public static final Map<String, AddrMode> addressModeTable;

    public static final Map<Mnemon, String> mnemonStringTable; 
    public static final Map<AddrMode, String> addressModeStringTable; 


    static{

        // Unary
        unaryMnemonTable = new HashMap<>();
        unaryMnemonTable.put("asla", Mnemon.M_ASLA);
        unaryMnemonTable.put("asra", Mnemon.M_ASRA);
        unaryMnemonTable.put("stop", Mnemon.M_STOP);

        // Dot-commands
        dotCmdMnemonTable = new HashMap<>();
        dotCmdMnemonTable.put("block", Mnemon.M_BLOCK);  //dot commands
        dotCmdMnemonTable.put("end"  , Mnemon.M_END); 

        // Non-Unary
        nonunaryMnemonTable = new HashMap<>();
        nonunaryMnemonTable.put("br"  , Mnemon.M_BR);     // single-argument
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

        // Addressing modes
        addressModeTable = new HashMap<>();
        addressModeTable.put("i",   AddrMode.AM_I);    // Immediate 
        addressModeTable.put("d",   AddrMode.AM_D);    // Direct
        addressModeTable.put("n",   AddrMode.AM_N);    // Indirect
        addressModeTable.put("s",   AddrMode.AM_S);    // Stack-relative
        addressModeTable.put("sf",  AddrMode.AM_SF);   // Stack-relative deferred 
        addressModeTable.put("x",   AddrMode.AM_X);    // Indexed 
        addressModeTable.put("sx",  AddrMode.AM_SX);   // Stack-indexed 
        addressModeTable.put("sfx", AddrMode.AM_SFX);  // Stack-deferred indexed 


        mnemonStringTable = new EnumMap<>(Mnemon.class);
        // Unary
        mnemonStringTable.put(Mnemon.M_ASLA, "asla");   
        mnemonStringTable.put(Mnemon.M_ASRA, "asra");
        mnemonStringTable.put(Mnemon.M_STOP, "stop");
        
        mnemonStringTable.put(Mnemon.M_BLOCK, ".block"); // dot commans
        mnemonStringTable.put(Mnemon.M_END,   ".end");
        // Non-Unary
        mnemonStringTable.put(Mnemon.M_BR,   "br");       // single-argument
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
        
        addressModeStringTable = new EnumMap<>(AddrMode.class); 
        addressModeStringTable.put(AddrMode.AM_I,   "i");   
        addressModeStringTable.put(AddrMode.AM_D,   "d");   
        addressModeStringTable.put(AddrMode.AM_N,   "n");   
        addressModeStringTable.put(AddrMode.AM_S,   "s");   
        addressModeStringTable.put(AddrMode.AM_SF,  "sf");   
        addressModeStringTable.put(AddrMode.AM_X,   "x");   
        addressModeStringTable.put(AddrMode.AM_SX,  "sx");   
        addressModeStringTable.put(AddrMode.AM_SFX, "sfx");   



    }

}
