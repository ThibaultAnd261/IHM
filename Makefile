# COMMANDES #
JAVAC = javac
# note $$ to get a single shell $
JAVAC_OPTIONS = -d build -sourcepath src -cp build:$$CLASSPATH -implicit:none
JAVA = java
JAR = jar
EXEC_JAR = ${JAVA} -jar

# CHEMINS RELATIFS
SRC = src/fr/iutfbleau/projetIHM2021FI2
BUILD = build/fr/iutfbleau/projetIHM2021FI2
DOC = doc/fr/iutfbleau/projetIHM2021FI2

# CHOIX NOMS

JAR_PROJET = projet.jar

# BUTS FACTICES #
.PHONY : run clean doc

# BUT PAR DEFAUT #

run : ${JAR_PROJET}
	${EXEC_JAR} ${JAR_PROJET}

# AUTRE BUTS
doc :
	javadoc -d doc src/fr/iutfbleau/projetIHM2021FI2/API/*.java src/fr/iutfbleau/projetIHM2021FI2/MNP/*.java src/fr/iutfbleau/projetIHM2021FI2/Sujet/Controller/*.java src/fr/iutfbleau/projetIHM2021FI2/Sujet/View/*.java src/fr/iutfbleau/projetIHM2021FI2/Sujet/Model/*.java

cleandoc :
	rm -rf doc

clean :
	rm -rf ${BUILD}/* *.jar

# REGLES DE DEPENDANCE #

## API ##
${BUILD}/API/MonPrint.class : ${SRC}/API/MonPrint.java 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/MonPrint.java

${BUILD}/API/TypeChambre.class : ${SRC}/API/TypeChambre.java 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/TypeChambre.java

${BUILD}/API/Chambre.class : ${SRC}/API/Chambre.java \
	  		   ${BUILD}/API/TypeChambre.class\
			     ${BUILD}/API/MonPrint.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/Chambre.java

${BUILD}/API/Client.class : ${SRC}/API/Client.java \
                            ${BUILD}/API/MonPrint.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/Client.java

${BUILD}/API/Prereservation.class : ${SRC}/API/Prereservation.java \
	  		            ${BUILD}/API/TypeChambre.class \
	  		     	    ${BUILD}/API/Client.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/Prereservation.java

${BUILD}/API/Reservation.class : ${SRC}/API/Reservation.java \
	  		         ${BUILD}/API/Prereservation.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/Reservation.java

${BUILD}/API/PrereservationFactory.class : ${SRC}/API/PrereservationFactory.java \
	  		            ${BUILD}/API/Prereservation.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/PrereservationFactory.java

${BUILD}/API/ReservationFactory.class : ${SRC}/API/ReservationFactory.java \
	  		            ${BUILD}/API/Prereservation.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/ReservationFactory.java

## MNP ##

${BUILD}/MNP/ClientNP.class : ${SRC}/MNP/ClientNP.java \
                              ${BUILD}/API/Client.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MNP/ClientNP.java

${BUILD}/MNP/ChambreNP.class : ${SRC}/MNP/ChambreNP.java \
                              ${BUILD}/API/Chambre.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MNP/ChambreNP.java

${BUILD}/MNP/PrereservationNP.class : ${SRC}/MNP/PrereservationNP.java \
			${BUILD}/MNP/ClientNP.class \
                        ${BUILD}/API/Prereservation.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MNP/PrereservationNP.java

${BUILD}/MNP/ReservationNP.class : ${SRC}/MNP/ReservationNP.java \
                              ${BUILD}/API/Reservation.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MNP/ReservationNP.java

${BUILD}/MNP/PrereservationFactory.class : ${SRC}/API/PrereservationFactory.java 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MNP/PrereservationFactory.java

${BUILD}/MNP/PrereservationFactoryNP.class : ${SRC}/MNP/PrereservationFactoryNP.java \
                              ${BUILD}/API/PrereservationFactory.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MNP/PrereservationFactoryNP.java

## TEST ##

 ${BUILD}/Test/TestTexteMNP.class : ${SRC}/Test/TestTexteMNP.java \
			 ${BUILD}/MNP/PrereservationNP.class \
			 ${BUILD}/MNP/PrereservationFactoryNP.class
	${JAVAC} -Xlint:deprecation ${JAVAC_OPTIONS} ${SRC}/Test/TestTexteMNP.java


## Application fonctionnelle ##

#############
##    BD   ##
#############

${BUILD}/BD/ConnexionBD.class : ${SRC}/BD/ConnexionBD.java
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/BD/ConnexionBD.java

${BUILD}/BD/ChambreBD.class : ${SRC}/BD/ChambreBD.java \
						${BUILD}/API/TypeChambre.class \
						${BUILD}/API/Chambre.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/BD/ChambreBD.java


${BUILD}/BD/ClientBD.class : ${SRC}/BD/ClientBD.java \
						${BUILD}/API/Client.class \
						${BUILD}/BD/ConnexionBD.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/BD/ClientBD.java


${BUILD}/BD/ReservationBD.class : ${SRC}/BD/ReservationBD.java \
					 	${BUILD}/BD/ConnexionBD.class \
					 	${BUILD}/API/TypeChambre.class \
						${BUILD}/API/Client.class \
						${BUILD}/BD/ClientBD.class \
						${BUILD}/API/Chambre.class \
						${BUILD}/API/Reservation.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/BD/ReservationBD.java

#############
## Sujet 1 ##
#############

${BUILD}/Sujet/Model/ConnectionModel.class : ${SRC}/Sujet/Model/ConnectionModel.java
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/Sujet/Model/ConnectionModel.java

# ChambreModel

${BUILD}/Sujet/Model/ChambreModel.class : ${SRC}/Sujet/Model/ChambreModel.java \
					${BUILD}/API/Chambre.class \
					${BUILD}/API/TypeChambre.class \
					${BUILD}/Sujet/Model/ConnectionModel.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/Sujet/Model/ChambreModel.java

# PrereservationModel

${BUILD}/Sujet/Model/PrereservationModel.class : ${SRC}/Sujet/Model/PrereservationModel.java \
					${BUILD}/API/Prereservation.class \
					${BUILD}/Sujet/Model/ConnectionModel.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/Sujet/Model/PrereservationModel.java

# PrereservationFactoryModel

${BUILD}/Sujet/Model/PrereservationFactoryModel.class : ${SRC}/Sujet/Model/PrereservationFactoryModel.java \
					${BUILD}/API/PrereservationFactory.class \
					${BUILD}/API/Prereservation.class \
					${BUILD}/Sujet/Model/ConnectionModel.class \
					${BUILD}/Sujet/Model/PrereservationModel.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/Sujet/Model/PrereservationFactoryModel.java


${BUILD}/Sujet/Model/ReservationModel.class : ${SRC}/Sujet/Model/ReservationModel.java \
					${BUILD}/API/Reservation.class \
					${BUILD}/API/Client.class \
					${BUILD}/API/Chambre.class \
					${BUILD}/Sujet/Model/ConnectionModel.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/Sujet/Model/ReservationModel.java


${BUILD}/Sujet/Controller/RetourAccueil.class : ${SRC}/Sujet/Controller/RetourAccueil.java \
					${SRC}/Sujet/View/Accueil.java
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/Sujet/Controller/RetourAccueil.java

${BUILD}/Sujet/View/RecapView.class : ${SRC}/Sujet/View/RecapView.java \
					${BUILD}/API/Reservation.class \
					${BUILD}/API/Client.class \
					${BUILD}/API/Chambre.class \
					${BUILD}/Sujet/Controller/RetourAccueil.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/Sujet/View/RecapView.java


${BUILD}/Sujet/Model/ClientModel.class : ${SRC}/Sujet/Model/ClientModel.java \
					${BUILD}/API/Client.class \
					${BUILD}/Sujet/Model/ConnectionModel.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/Sujet/Model/ClientModel.java

${BUILD}/Sujet/Controller/UpdateView.class : ${SRC}/Sujet/Controller/UpdateView.java \
					${SRC}/Sujet/View/MultipleResultat.java \
					${BUILD}/Sujet/Model/ChambreModel.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/Sujet/Controller/UpdateView.java

${BUILD}/Sujet/Controller/ValidationReservation.class : ${SRC}/Sujet/Controller/ValidationReservation.java \
					${SRC}/Sujet/View/MultipleResultat.java \
					${SRC}/Sujet/View/Resultat.java \
					${BUILD}/Sujet/Model/ChambreModel.class \
					${BUILD}/API/Chambre.class \
					${BUILD}/Sujet/View/RecapView.class \
					${BUILD}/API/ReservationFactory.class \
					${BUILD}/API/Prereservation.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/Sujet/Controller/ValidationReservation.java

${BUILD}/Sujet/Model/ReservationFactoryModel.class : ${SRC}/Sujet/Model/ReservationFactoryModel.java \
					${BUILD}/Sujet/Model/ReservationModel.class \
					${BUILD}/Sujet/Model/ConnectionModel.class \
					${BUILD}/API/Prereservation.class \
					${BUILD}/API/Reservation.class \
					${BUILD}/API/ReservationFactory.class \
					${BUILD}/API/Chambre.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/Sujet/Model/ReservationFactoryModel.java

${BUILD}/Sujet/View/Resultat.class : ${SRC}/Sujet/View/Resultat.java \
					${BUILD}/API/Prereservation.class \
					${BUILD}/API/Client.class \
					${BUILD}/Sujet/Controller/ValidationReservation.class \
					${BUILD}/API/Chambre.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/Sujet/View/Resultat.java

${BUILD}/Sujet/View/MultipleResultat.class : ${SRC}/Sujet/View/MultipleResultat.java \
					${BUILD}/API/Prereservation.class \
					${BUILD}/Sujet/Model/ClientModel.class \
					${BUILD}/API/Client.class \
					${BUILD}/Sujet/Controller/UpdateView.class \
					${BUILD}/API/Chambre.class \
					${BUILD}/Sujet/Model/ConnectionModel.class \
					${BUILD}/Sujet/Model/ChambreModel.class \
					${BUILD}/Sujet/Model/ReservationFactoryModel.class \
					${BUILD}/Sujet/Controller/ValidationReservation.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/Sujet/View/MultipleResultat.java

${BUILD}/Sujet/Controller/PrereservationController.class : ${SRC}/Sujet/Controller/PrereservationController.java \
					${BUILD}/Sujet/View/Resultat.class \
					${BUILD}/Sujet/Model/ClientModel.class \
					${BUILD}/Sujet/Model/ReservationModel.class \
					${BUILD}/Sujet/Model/ConnectionModel.class \
					${BUILD}/Sujet/Model/PrereservationFactoryModel.class \
					${BUILD}/API/Prereservation.class \
					${BUILD}/API/Client.class \
					${BUILD}/Sujet/Model/ReservationFactoryModel.class \
					${BUILD}/Sujet/View/MultipleResultat.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/Sujet/Controller/PrereservationController.java

################################



################################

${BUILD}/Sujet/View/AccueilView.class : ${SRC}/Sujet/View/AccueilView.java \
					${BUILD}/Sujet/Controller/PrereservationController.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/Sujet/View/AccueilView.java

################################

###########
# Sujet 2 #
###########

${BUILD}/Sujet/Controller/ButtonController.class : ${SRC}/Sujet/Controller/ButtonController.java
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/Sujet/Controller/ButtonController.java

# View sujet 2
${BUILD}/Sujet/View/Graph.class : ${SRC}/Sujet/View/Graph.java \
					${BUILD}/Sujet/Controller/ButtonController.class \
					${BUILD}/Sujet/Controller/RetourAccueil.class \
					${BUILD}/Sujet/Model/ReservationFactoryModel.class \
					${BUILD}/API/TypeChambre.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/Sujet/View/Graph.java


##################
# Page d'accueil #
##################

# Contrôleur de la page d'accueil
${BUILD}/Sujet/Controller/AccueilController.class : ${SRC}/Sujet/Controller/AccueilController.java \
					${BUILD}/Sujet/View/Graph.class \
					${BUILD}/Sujet/View/AccueilView.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/Sujet/Controller/AccueilController.java

${BUILD}/Sujet/View/Accueil.class : ${SRC}/Sujet/View/Accueil.java \
					${BUILD}/Sujet/Controller/AccueilController.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/Sujet/View/Accueil.java

${BUILD}/Sujet/Main.class : ${SRC}/Sujet/Main.java \
					${BUILD}/Sujet/View/Accueil.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/Sujet/Main.java


# ## JARS ##

 ${JAR_PROJET} : ${BUILD}/Sujet/Main.class
	${JAR} cvfe ${JAR_PROJET} fr.iutfbleau.projetIHM2021FI2.Sujet.Main -C build fr org
