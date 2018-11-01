package datamodel;

import java.io.Serializable;



public class Record implements Serializable
{
    private Long recordId;
    private String recordName;

    
    
    public Record()
    {
    }

    
    
    public Record(Long recordId, String recordName)
    {
        this.recordId = recordId;
        this.recordName = recordName;
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recordId != null ? recordId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        if (!(object instanceof Record))
        {
            return false;
        }
        
        Record other = (Record) object;
        
        if ((this.recordId == null && other.recordId != null) || (this.recordId != null && !this.recordId.equals(other.recordId))) 
        {
            return false;
        }
        
        return true;
    }

    @Override
    public String toString() 
    {
        return "datamodel.Record[ recordId=" + recordId + "; recordName=" + recordName + " ]";
    }

    
    
    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }
}
