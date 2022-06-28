Como construir uma Dungeon:

Para começar na primeira linha insira o player com "Player x y Energia", onde x y é sua posicao e Energia é a quantidade de energia inicial.
ex: Player 0 0 12

Depois digite "x y NomeDaCelula" para todas as celulas; caso não haja nome ou o nome não exista no jogo, será inserido uma CelulaPadrao;
caso não seja inserido uma coordenada x y necessária, será colocado uma parede.
Quando digitar todas as celulas escreva um "END"
ex:
0 0 CelulaPadrao
0 1 CelulaPadrao
0 2
0 3
0 4
0 5
0 6
0 7
0 8
0 9
0 10 Saida
1 0 
2 0
3 0
4 0
5 0
END

Depois digite os componentes que serão inseridos no formato "NomeDoComponente x y ...OutrasVariaveisNecessarias"
Caso não seja especificado corretamente, o componente não será inserido.
ex:
Energia 0 2 3
Energia 0 3 5
Porta 0 5 false
Alavanca 5 0 0 5

As especificações para cada componente estão em COMPONENTES.txt
E por fim titule o arquivo como "[Número do Nível].txt"