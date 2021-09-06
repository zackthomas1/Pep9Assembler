package main.model.parser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Maps {
    
    public static final Map<String, Mnemon> unaryMnemonTable; 
    public static final Map<String, Mnemon> dotCmdMnemonTable;
    public static final Map<String, Mnemon> nonunaryMnemonTable; 
    public static final Map<String, AddressMode> addressModeTable;
    public static final Map<Mnemon, List<AddressMode> > MnemonValidAddresses; 

    public static final Map<Mnemon, String> mnemonStringTable; 
    public static final Map<AddressMode, String> addressModeStringTable; 

    public static Map<String, Integer> symbolTable;
    public static Map<Integer, String> symbolAddressTable;

    static{

        // Unary
        unaryMnemonTable = new HashMap<>();
        unaryMnemonTable.put("asla", Mnemon.M_ASLA);
        unaryMnemonTable.put("aslx", Mnemon.M_ASLX);  
        unaryMnemonTable.put("asra", Mnemon.M_ASRA);
        unaryMnemonTable.put("asrx", Mnemon.M_ASRX);
        unaryMnemonTable.put("rola", Mnemon.M_ROLA);
        unaryMnemonTable.put("rolx", Mnemon.M_ROLX);
        unaryMnemonTable.put("rora", Mnemon.M_RORA);
        unaryMnemonTable.put("rorx", Mnemon.M_RORX);
        unaryMnemonTable.put("nota", Mnemon.M_NOTA);
        unaryMnemonTable.put("notx", Mnemon.M_NOTX);
        unaryMnemonTable.put("nega", Mnemon.M_NEGA);
        unaryMnemonTable.put("negx", Mnemon.M_NEGX);
        unaryMnemonTable.put("movspa", Mnemon.M_MOVSPA);
        unaryMnemonTable.put("movflga", Mnemon.M_MOVFLGA);
        unaryMnemonTable.put("movaflg", Mnemon.M_MOVAFLG);
        unaryMnemonTable.put("rettr", Mnemon.M_RETTR);
        unaryMnemonTable.put("ret", Mnemon.M_RET);
        unaryMnemonTable.put("stop", Mnemon.M_STOP);

        // Dot-commands
        dotCmdMnemonTable = new HashMap<>();
        dotCmdMnemonTable.put(".block", Mnemon.M_BLOCK);     //dot commands
        dotCmdMnemonTable.put(".end"  , Mnemon.M_END); 

        // Non-Unary
        nonunaryMnemonTable = new HashMap<>();
        // non-addressed
        nonunaryMnemonTable.put("br"  , Mnemon.M_BR);       
        nonunaryMnemonTable.put("brle", Mnemon.M_BRLE);
        nonunaryMnemonTable.put("brlt", Mnemon.M_BRLT);
        nonunaryMnemonTable.put("breq", Mnemon.M_BREQ);
        nonunaryMnemonTable.put("brne", Mnemon.M_BRNE);  
        nonunaryMnemonTable.put("brge", Mnemon.M_BRGE);       
        nonunaryMnemonTable.put("brgt", Mnemon.M_BRGT);
        nonunaryMnemonTable.put("brv", Mnemon.M_BRV);
        nonunaryMnemonTable.put("brc", Mnemon.M_BRC);
        nonunaryMnemonTable.put("call", Mnemon.M_CALL);  
        // addressed
        nonunaryMnemonTable.put("cpwa", Mnemon.M_CPWA);    
        nonunaryMnemonTable.put("deci", Mnemon.M_DECI);
        nonunaryMnemonTable.put("deco", Mnemon.M_DECO);
        nonunaryMnemonTable.put("adda", Mnemon.M_ADDA);
        nonunaryMnemonTable.put("suba", Mnemon.M_SUBA);
        nonunaryMnemonTable.put("stwa", Mnemon.M_STWA);
        nonunaryMnemonTable.put("ldwa", Mnemon.M_LDWA); 
        
        // Addressing modes
        addressModeTable = new HashMap<>();
        addressModeTable.put("i",   AddressMode.AM_I);    // Immediate 
        addressModeTable.put("d",   AddressMode.AM_D);    // Direct
        addressModeTable.put("n",   AddressMode.AM_N);    // Indirect
        addressModeTable.put("s",   AddressMode.AM_S);    // Stack-relative
        addressModeTable.put("sf",  AddressMode.AM_SF);   // Stack-relative deferred 
        addressModeTable.put("x",   AddressMode.AM_X);    // Indexed 
        addressModeTable.put("sx",  AddressMode.AM_SX);   // Stack-indexed 
        addressModeTable.put("sfx", AddressMode.AM_SFX);  // Stack-deferred indexed 

        // mneomic strings
        // --------------------------------------------
        mnemonStringTable = new EnumMap<>(Mnemon.class);
        // Unary
        mnemonStringTable.put(Mnemon.M_ASLA, "asla");  
        mnemonStringTable.put(Mnemon.M_ASLX, "aslx");  
        mnemonStringTable.put(Mnemon.M_ASRA, "asra");
        mnemonStringTable.put(Mnemon.M_ASRX, "asrx");
        mnemonStringTable.put(Mnemon.M_ROLA, "rola");
        mnemonStringTable.put(Mnemon.M_ROLX, "rolx");
        mnemonStringTable.put(Mnemon.M_RORA, "rora");
        mnemonStringTable.put(Mnemon.M_RORX, "rorx");
        mnemonStringTable.put(Mnemon.M_NOTA, "nota");
        mnemonStringTable.put(Mnemon.M_NOTX, "notx");
        mnemonStringTable.put(Mnemon.M_NEGA, "nega");
        mnemonStringTable.put(Mnemon.M_NEGX, "negx");
        mnemonStringTable.put(Mnemon.M_MOVSPA, "movspa");
        mnemonStringTable.put(Mnemon.M_MOVFLGA, "movflga");
        mnemonStringTable.put(Mnemon.M_MOVAFLG, "movaflg");
        mnemonStringTable.put(Mnemon.M_RETTR, "rettr");
        mnemonStringTable.put(Mnemon.M_RET, "ret");
        mnemonStringTable.put(Mnemon.M_STOP, "stop");
        
        // Dot Commands
        mnemonStringTable.put(Mnemon.M_BLOCK, ".block");  
        mnemonStringTable.put(Mnemon.M_END,   ".end");

        // Non-Unary
        // non-addressed
        mnemonStringTable.put(Mnemon.M_BR,      "br");        
        mnemonStringTable.put(Mnemon.M_BRLE,    "brle");
        mnemonStringTable.put(Mnemon.M_BRLT,    "brlt");
        mnemonStringTable.put(Mnemon.M_BREQ,    "breq");
        mnemonStringTable.put(Mnemon.M_BRNE,    "brne");        
        mnemonStringTable.put(Mnemon.M_BRGE,    "brge");
        mnemonStringTable.put(Mnemon.M_BRGT,    "brgt");
        mnemonStringTable.put(Mnemon.M_BRV,     "brv");
        mnemonStringTable.put(Mnemon.M_BRC,     "brc");
        mnemonStringTable.put(Mnemon.M_CALL,    "call");
        // addressed
        mnemonStringTable.put(Mnemon.M_ADDA, "adda");       
        mnemonStringTable.put(Mnemon.M_CPWA, "cpwa");   
        mnemonStringTable.put(Mnemon.M_DECI, "deci");
        mnemonStringTable.put(Mnemon.M_DECO, "deco");
        mnemonStringTable.put(Mnemon.M_LDWA, "ldwa"); 
        mnemonStringTable.put(Mnemon.M_SUBA, "suba");
        mnemonStringTable.put(Mnemon.M_STWA, "stwa");
        
        // Addressing Modes
        addressModeStringTable = new EnumMap<>(AddressMode.class); 
        addressModeStringTable.put(AddressMode.AM_I,   "i");   
        addressModeStringTable.put(AddressMode.AM_D,   "d");   
        addressModeStringTable.put(AddressMode.AM_N,   "n");   
        addressModeStringTable.put(AddressMode.AM_S,   "s");   
        addressModeStringTable.put(AddressMode.AM_SF,  "sf");   
        addressModeStringTable.put(AddressMode.AM_X,   "x");   
        addressModeStringTable.put(AddressMode.AM_SX,  "sx");   
        addressModeStringTable.put(AddressMode.AM_SFX, "sfx");   

        MnemonValidAddresses = new HashMap<>();
        // non addressed
        MnemonValidAddresses.put(Mnemon.M_BR, 
            new ArrayList<AddressMode>(Arrays.asList(AddressMode.AM_I,AddressMode.AM_X)));
        MnemonValidAddresses.put(Mnemon.M_BRLE, 
            new ArrayList<AddressMode>(Arrays.asList(AddressMode.AM_I,AddressMode.AM_X)));
        MnemonValidAddresses.put(Mnemon.M_BRLT, 
            new ArrayList<AddressMode>(Arrays.asList(AddressMode.AM_I,AddressMode.AM_X)));
        MnemonValidAddresses.put(Mnemon.M_BREQ, 
            new ArrayList<AddressMode>(Arrays.asList(AddressMode.AM_I,AddressMode.AM_X)));
        MnemonValidAddresses.put(Mnemon.M_BRNE, 
            new ArrayList<AddressMode>(Arrays.asList(AddressMode.AM_I,AddressMode.AM_X)));
        MnemonValidAddresses.put(Mnemon.M_BRGE, 
            new ArrayList<AddressMode>(Arrays.asList(AddressMode.AM_I,AddressMode.AM_X)));
        MnemonValidAddresses.put(Mnemon.M_BRGT, 
            new ArrayList<AddressMode>(Arrays.asList(AddressMode.AM_I,AddressMode.AM_X)));
        MnemonValidAddresses.put(Mnemon.M_BRV, 
            new ArrayList<AddressMode>(Arrays.asList(AddressMode.AM_I,AddressMode.AM_X)));
        MnemonValidAddresses.put(Mnemon.M_BRC, 
            new ArrayList<AddressMode>(Arrays.asList(AddressMode.AM_I,AddressMode.AM_X)));
        MnemonValidAddresses.put(Mnemon.M_CALL, 
            new ArrayList<AddressMode>(Arrays.asList(AddressMode.AM_I,AddressMode.AM_X)));
        // addressed
        MnemonValidAddresses.put(Mnemon.M_ADDA, 
            new ArrayList<AddressMode>(Arrays.asList(AddressMode.AM_I,AddressMode.AM_D,AddressMode.AM_N,AddressMode.AM_S,AddressMode.AM_SF,AddressMode.AM_X,AddressMode.AM_SX,AddressMode.AM_SFX)));
        MnemonValidAddresses.put(Mnemon.M_CPWA, 
            new ArrayList<AddressMode>(Arrays.asList(AddressMode.AM_I,AddressMode.AM_D,AddressMode.AM_N,AddressMode.AM_S,AddressMode.AM_SF,AddressMode.AM_X,AddressMode.AM_SX,AddressMode.AM_SFX)));
        MnemonValidAddresses.put(Mnemon.M_DECI, 
            new ArrayList<AddressMode>(Arrays.asList(AddressMode.AM_D,AddressMode.AM_N,AddressMode.AM_S,AddressMode.AM_SF,AddressMode.AM_X,AddressMode.AM_SX,AddressMode.AM_SFX)));
        MnemonValidAddresses.put(Mnemon.M_DECO, 
            new ArrayList<AddressMode>(Arrays.asList(AddressMode.AM_I,AddressMode.AM_D,AddressMode.AM_N,AddressMode.AM_S,AddressMode.AM_SF,AddressMode.AM_X,AddressMode.AM_SX,AddressMode.AM_SFX)));
        MnemonValidAddresses.put(Mnemon.M_LDWA, 
            new ArrayList<AddressMode>(Arrays.asList(AddressMode.AM_I,AddressMode.AM_D,AddressMode.AM_N,AddressMode.AM_S,AddressMode.AM_SF,AddressMode.AM_X,AddressMode.AM_SX,AddressMode.AM_SFX)));
        MnemonValidAddresses.put(Mnemon.M_SUBA, 
            new ArrayList<AddressMode>(Arrays.asList(AddressMode.AM_I,AddressMode.AM_D,AddressMode.AM_N,AddressMode.AM_S,AddressMode.AM_SF,AddressMode.AM_X,AddressMode.AM_SX,AddressMode.AM_SFX)));
        MnemonValidAddresses.put(Mnemon.M_STWA, 
            new ArrayList<AddressMode>(Arrays.asList(AddressMode.AM_D,AddressMode.AM_N,AddressMode.AM_S,AddressMode.AM_SF,AddressMode.AM_X,AddressMode.AM_SX,AddressMode.AM_SFX)));

    
    
        }

}
