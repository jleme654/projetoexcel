/**
 *
 * @author Julio (Upaid)
 * 13/05/2010
 *  
 *  */

package net.upaid.model;

public class Transaction {

    private int telareacode;
    private int telnumber;
    private int nsufinance;
    private char status = 'N';
    
    
    

    public int getNsufinance() {
        return nsufinance;
    }

    public void setNsufinance(int nsufinance) {
        this.nsufinance = nsufinance;
    }

    public int getTelareacode() {
        return telareacode;
    }

    public void setTelareacode(int telareacode) {
        this.telareacode = telareacode;
    }

    public int getTelnumber() {
        return telnumber;
    }

    public void setTelnumber(int telnumber) {
        this.telnumber = telnumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Transaction other = (Transaction) obj;
        if (this.telareacode != other.telareacode) {
            return false;
        }
        if (this.telnumber != other.telnumber) {
            return false;
        }
        if (this.nsufinance != other.nsufinance) {
            return false;
        }
        return true;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        if (status != 'E' && status != 'C' && status != 'N') {
            throw new IllegalArgumentException("Status deve ser E, C ou N");
        }
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.telareacode;
        hash = 23 * hash + this.telnumber;
        hash = 23 * hash + this.nsufinance;
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("[");
        sb.append(telareacode);
        sb.append(", ");
        sb.append(telnumber);
        sb.append(", ");
        sb.append(nsufinance);
        sb.append("]\n");

        return sb.toString();
    }

}
