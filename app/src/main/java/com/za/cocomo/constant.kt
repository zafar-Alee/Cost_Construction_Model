package com.za.cocomo

object constant {
    private lateinit var datalist: ArrayList<rv_model>

    fun getData(): ArrayList<rv_model> {
        datalist = ArrayList()

        datalist.add(
            rv_model(
                "RELY", 0.75, 0.88, 1.00, 1.15, 1.40,
                selectedCheckboxIndex = null, // Initially null (not selected)
                calculatedProduct = 1.0 // Default product (no multiplier applied yet)
            )
        )
        datalist.add(
            rv_model(
                "DATA", null, 0.94, 1.00, 1.08, 1.16,
                selectedCheckboxIndex = null,
                calculatedProduct = 1.0
            )
        )
        datalist.add(
            rv_model(
                "CPLX", 0.70, 0.85, 1.00, 1.15, 1.30,
                selectedCheckboxIndex = null,
                calculatedProduct = 1.0
            )
        )
        datalist.add(
            rv_model(
                "TIME", null, null, 1.00, 1.11, 1.30,
                selectedCheckboxIndex = null,
                calculatedProduct = 1.0
            )
        )
        datalist.add(
            rv_model(
                "STOR", null, null, 1.00, 1.06, 1.21,
                selectedCheckboxIndex = null,
                calculatedProduct = 1.0
            )
        )
        datalist.add(
            rv_model(
                "VIRT", 0.0, 0.87, 1.00, 1.15, 1.30,
                selectedCheckboxIndex = null,
                calculatedProduct = 1.0
            )
        )
        datalist.add(
            rv_model(
                "TURN", null, 0.87, 1.00, 1.07, 1.15,
                selectedCheckboxIndex = null,
                calculatedProduct = 1.0
            )
        )
        datalist.add(
            rv_model(
                "ACAP", 1.46, 1.19, 1.00, 0.86, 0.71,
                selectedCheckboxIndex = null,
                calculatedProduct = 1.0
            )
        )
        datalist.add(
            rv_model(
                "AEXP", 1.29, 1.13, 1.00, 0.91, 0.82,
                selectedCheckboxIndex = null,
                calculatedProduct = 1.0
            )
        )
        datalist.add(
            rv_model(
                "PCAP", 1.42, 1.17, 1.00, 0.86, 0.70,
                selectedCheckboxIndex = null,
                calculatedProduct = 1.0
            )
        )
        datalist.add(
            rv_model(
                "VEXP", 1.21, 1.10, 1.00, 0.90, 0.0,
                selectedCheckboxIndex = null,
                calculatedProduct = 1.0
            )
        )
        datalist.add(
            rv_model(
                "LEXP", 1.14, 1.07, 1.00, 0.95, null,
                selectedCheckboxIndex = null,
                calculatedProduct = 1.0
            )
        )
        datalist.add(
            rv_model(
                "MODP", 1.24, 1.10, 1.00, 0.91, 0.82,
                selectedCheckboxIndex = null,
                calculatedProduct = 1.0
            )
        )
        datalist.add(
            rv_model(
                "TOOL", 1.24, 1.10, 1.00, 0.91, 0.83,
                selectedCheckboxIndex = null,
                calculatedProduct = 1.0
            )
        )
        datalist.add(
            rv_model(
                "SCED", 1.23, 1.08, 1.00, 1.04, 1.10,
                selectedCheckboxIndex = null,
                calculatedProduct = 1.0
            )
        )

        return datalist
    }
}
