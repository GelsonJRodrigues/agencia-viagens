```Mermaid
classDiagram

    class Cliente {
        +int id_cliente
        +string nome
        +string endereco
        +string telefone
        +string email
        +fazerReserva()
    }

    class Reserva {
        +int id_reserva
        +date data_reserva
        +date data_viagem
        +int id_cliente
        +int id_pacote
        +calcularTotal()
    }

    class Pacote {
        +int id_pacote
        +string descricao
        +float preco
        +int duracao_dias
        +listarPacotes()
    }

    class Pagamento {
        +int id_pagamento
        +date data_pagamento
        +float valor_pago
        +string metodo_pagamento
        +int id_reserva
        +efetuarPagamento()
    }

    Cliente  "1" --> "*" Reserva : faz
    Pacote  "1" --> "*" Reserva : inclui
    Reserva  "1" --> "1" Pagamento : gera
```
