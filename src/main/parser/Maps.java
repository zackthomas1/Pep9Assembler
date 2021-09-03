package main.parser;
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

    static{

        // Unary
        unaryMnemonTable = new HashMap<>();
        unaryMnemonTable.put("asla", Mnemon.M_ASLA);
        unaryMnemonTable.put("asra", Mnemon.M_ASRA);
        unaryMnemonTable.put("stop", Mnemon.M_STOP);

        // Dot-commands
        dotCmdMnemonTable = new HashMap<>();
        dotCmdMnemonTable.put(".block", Mnemon.M_BLOCK);     //dot commands
        dotCmdMnemonTable.put(".end"  , Mnemon.M_END); 

        // Non-Unary
        nonunaryMnemonTable = new HashMap<>();
        // non-addressed
        nonunaryMnemonTable.put("br"  , Mnemon.M_BR);       
        nonunaryMnemonTable.put("brlt", Mnemon.M_BRLT);
        nonunaryMnemonTable.put("breq", Mnemon.M_BREQ);
        nonunaryMnemonTable.put("brle", Mnemon.M_BRLE);
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
        mnemonStringTable.put(Mnemon.M_ASRA, "asra");
        mnemonStringTable.put(Mnemon.M_STOP, "stop");
        
        // Dot Commands
        mnemonStringTable.put(Mnemon.M_BLOCK, ".block");  
        mnemonStringTable.put(Mnemon.M_END,   ".end");

        // Non-Unary
        // non-addressed
        mnemonStringTable.put(Mnemon.M_BR,   "br");        
        mnemonStringTable.put(Mnemon.M_BRLT, "brlt");
        mnemonStringTable.put(Mnemon.M_BREQ, "breq");
        mnemonStringTable.put(Mnemon.M_BRLE, "brle");
        mnemonStringTable.put(Mnemon.M_CALL, "call");
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
        MnemonValidAddresses.put(Mnemon.M_BRLT, 
            new ArrayList<AddressMode>(Arrays.asList(AddressMode.AM_I,AddressMode.AM_X)));
        MnemonValidAddresses.put(Mnemon.M_BREQ, 
            new ArrayList<AddressMode>(Arrays.asList(AddressMode.AM_I,AddressMode.AM_X)));
        MnemonValidAddresses.put(Mnemon.M_BRLE, 
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
