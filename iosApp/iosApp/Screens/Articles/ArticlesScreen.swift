//
//  ArticlesScreen.swift
//  iosApp
//
//  Created by Rapha on 21/04/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct ArticlesScreen: View {
    @State var shouldOpenAbout = false
    
    var body: some View {
        NavigationStack {
            ArticlesView()
                .navigationTitle("Articles")
                .toolbar {
                    toolbarContentView($shouldOpenAbout)
                }
        }
    }
}

private struct ArticlesView: View {
    @ObservedObject private(set) var viewModel: ArticlesViewModelWrapper = ArticlesView.ArticlesViewModelWrapper()
    
    var body: some View {
        VStack {
            switch onEnum(of: viewModel.state) {
            case .loading:
                LoadingView()
            case .success(let data):
                ContentView(articles: data.articles)
            case .error(let data):
                ErrorView(message: data.message)
            }
        }
        .onAppear {
            viewModel.startObserving()
        }
    }
}

private struct LoadingView: View {
    var body: some View {
        ProgressView()
    }
}

private struct ErrorView: View {
    let message: String
    
    var body: some View {
        Text(message)
    }
}

private struct ContentView: View {
    let articles: [Article]
    
    var body: some View {
        ScrollView {
            LazyVStack(spacing: 10) {
                ForEach(articles, id: \.self) { article in
                    ArticleItemView(article: article)
                }
            }
        }
    }
}

private struct ArticleItemView: View {
    var article: Article
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            AsyncImage(url: URL(string: article.imageUrl)) { phase in
                if phase.image != nil {
                    phase.image!
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                } else if phase.error != nil {
                    Text("Image Load Error")
                } else {
                    ProgressView()
                }
            }
            Text(article.title)
                .font(.title)
                .fontWeight(.bold)
            Text(article.articleDescription)
            Text(article.date).frame(maxWidth: .infinity, alignment: .trailing).foregroundStyle(.gray)
        }
        .padding(16)
    }
}

@ToolbarContentBuilder
private func toolbarContentView(_ shouldOpenAbout: Binding<Bool>) -> some ToolbarContent {
    ToolbarItem {
        Button {
            shouldOpenAbout.wrappedValue = true
        } label: {
            Label("About", systemImage: "info.circle").labelStyle(.titleAndIcon)
        }
        .popover(isPresented: shouldOpenAbout) {
            AboutScreen()
        }
    }
}

extension ArticlesView {
    @MainActor
    class ArticlesViewModelWrapper: ObservableObject {
        let viewModel: ArticlesViewModel
        
        init() {
            viewModel = ArticlesViewModel()
            state = viewModel.state.value
        }
        
        @Published var state: ArticlesState
        
        func startObserving() {
            viewModel.getArticles()
            Task {
                for await state in viewModel.state {
                    self.state = state
                }
            }
        }
    }
}

#Preview {
    ArticlesScreen()
}
