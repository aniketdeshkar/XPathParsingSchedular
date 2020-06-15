package com.ad.constant;

public enum XpathEnum {
	
	SCREENINGFIELD_1("Document/CstmrCdtTrfInitn/PmtInf/Dbtr/Nm"),
	SCREENINGFIELD_2("Document/CstmrCdtTrfInitn/GrpHdr/MsgId");
	
	// declaring private variable for getting values 
    private String value; 
  
    // getter method
    public String getValue() {
		return value;
	} 
    
    // enum constructor - cannot be public or protected 
    private XpathEnum(String value) 
    { 
        this.value = value; 
    }

}
