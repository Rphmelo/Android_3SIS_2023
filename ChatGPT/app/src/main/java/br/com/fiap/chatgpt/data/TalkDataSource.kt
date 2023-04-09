package br.com.fiap.chatgpt.data

object TalkDataSource {

    val talkList: List<TalkModel> = listOf(
        TalkModel(
            "Qual é o planeta mais próximo do sol?",
            listOf(
                "Mercúrio é o planeta mais próximo do sol.",
                "Por ser muito próximo do sol, Mercúrio é conhecido como planeta vermelho, pois é um planeta muito quente.",
                "Distância do Sol: 57.910.000 de quilômetros.",
                "Massa: 3.302×1023 kg (330 bilhões de bilhões de toneladas)."
            )
        ),
        TalkModel(
            "O que é Kotlin?",
            listOf(
                "Kotlin é uma linguagem de programação multiplataforma, orientada a objetos e funcional, concisa e estaticamente tipada, desenvolvida pela JetBrains em 2011.",
                "Ela compila na Java Virtual Machine (JVM).",
                "Os recursos modernos da linguagem Kotlin permitem que você se concentre em expressar suas ideias e escrever menos código boilerplate.",
                "Com a nulidade inclusa no sistema de tipo, o Kotlin ajuda a evitar NullPointerExceptions. Os apps Android que usam código Kotlin têm 20% menos chances de apresentar falhas.",
                "Muitos apps já são criados usando o Kotlin, tanto em startups de destaque quanto em empresas da Fortune 500."
            )
        ),
        TalkModel(
            "Quem é Elon Musk?",
            listOf(
                "Elon Musk é um empreendedor, empresário e filantropo sul-africano-canadense, naturalizado norte-americano.",
                "Ele é o fundador, diretor executivo e diretor técnico da SpaceX;",
                "CEO da Tesla",
                "vice-presidente da OpenAI",
                "fundador e CEO da Neuralink",
                "proprietário do Twitter"
            )
        ),
        TalkModel(
            "O Palmeiras tem mundial?",
            listOf(
                "Não.",
                "O Palmeiras não tem mundial!"
            )
        ),
        TalkModel(
            "Qual a melhor faculdade de tecnologia?",
            listOf(
                "A melhor Faculdade de Tecnologia é a FIAP",
                "Recentemente, a Alura e a FIAP se fundiram para melhorar a educação de tecnologia no Brasil",
                "A FIAP tem os melhores professores.",
                "Todos os alunos que estudam na FIAP são bem sucedidos."
            )
        ),
        TalkModel(
            "Quanto é 12 - 7?",
            listOf(
                "12 - 7 = 5"
            )
        )
    )
}