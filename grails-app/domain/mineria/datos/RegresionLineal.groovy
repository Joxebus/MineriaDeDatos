package mineria.datos

class RegresionLineal {

    BigDecimal varianzaXX
    BigDecimal varianzaYY
    BigDecimal varianzaXY

    static constraints = {
        varianzaXX  nullable:true
        varianzaYY  nullable:true
        varianzaXY  nullable:true
    }
}
