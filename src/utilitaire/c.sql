  create table Client (IDClient int not null primary key auto_increment, nom varchar(40) not null, tel int);
  create table Commande (IDCommande int not null primary key auto_increment, description varchar(50) not null, dateCmd date not null, IDClient int not null,
                          foreign key (IDClient) references Client(IDClient));
					
  create table Facture(IDFacture int not null primary key auto_increment, montant float not null, dateFact date not null,IDCommande int not null,
                        foreign key (IDCommande) references Commande(IDCommande));
  create table Produit(reference varchar(15) not null primary key, designation varchar(40), prix float not null);
  create table Details(reference varchar(15) not null,IDCommande int not null, Qte int not null,
                       primary key(reference,IDCommande),
                       foreign key(reference) references Produit(reference),
					   foreign key(IDCommande) reference Commande(IDCommande));
					  
  create table Rayon(IDRayon int not null primary key auto_increment, etiquette varchar(5) not null unique);
  
  create table Produit_Rayon(reference varchar(15) not null,IDRayon int not null, Qte int not null,
                             primary key(reference,IDRayon),
							 foreign key(reference) references Produit(reference),
							 foreign key(IDRayon) references Rayon(IDRayon));
							 
  create index idxNom on Client(nom);