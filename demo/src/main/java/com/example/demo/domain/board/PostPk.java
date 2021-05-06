package com.example.demo.domain.board;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class PostPk implements Serializable {
	private String createrId;
	private Long createSeq;
}
