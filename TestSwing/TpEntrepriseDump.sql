USE [master]
GO

/****** Object:  Database [TPEntreprise]    Script Date: 30/11/2016 11:02:07 ******/
CREATE DATABASE [TPEntreprise];
USE [TPEntreprise]
GO
/****** Object:  Table [dbo].[personne]    Script Date: 30/11/2016 11:05:25 ******/
DROP TABLE [dbo].[personne]
GO

/****** Object:  Table [dbo].[personne]    Script Date: 30/11/2016 11:05:25 ******/

CREATE TABLE [dbo].[personne](
	[idPersonne] [int] IDENTITY(1,1) NOT NULL,
	[nom] [varchar](50) NULL,
	[prenom] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[idPersonne] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO



/****** Object:  Table [dbo].[equipe]    Script Date: 30/11/2016 11:04:44 ******/
DROP TABLE [dbo].[equipe]
GO

/****** Object:  Table [dbo].[equipe]    Script Date: 30/11/2016 11:04:44 ******/


CREATE TABLE [dbo].[equipe](
	[idEquipe] [int] IDENTITY(1,1) NOT NULL,
	[nomEquipe] [varchar](50) NULL,
	[idLeader] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[idEquipe] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO


ALTER TABLE [dbo].[equipe]  WITH CHECK ADD FOREIGN KEY([idLeader])
REFERENCES [dbo].[personne] ([idPersonne])
GO

/****** Object:  Table [dbo].[assoEquipePersonne]    Script Date: 30/11/2016 11:03:55 ******/
DROP TABLE [dbo].[assoEquipePersonne]
GO

/****** Object:  Table [dbo].[assoEquipePersonne]    Script Date: 30/11/2016 11:03:55 ******/

CREATE TABLE [dbo].[assoEquipePersonne](
	[idEquipe] [int] NOT NULL,
	[idPersonne] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[idEquipe] ASC,
	[idPersonne] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[assoEquipePersonne]  WITH CHECK ADD FOREIGN KEY([idEquipe])
REFERENCES [dbo].[equipe] ([idEquipe])
GO

ALTER TABLE [dbo].[assoEquipePersonne]  WITH CHECK ADD FOREIGN KEY([idPersonne])
REFERENCES [dbo].[personne] ([idPersonne])
GO




