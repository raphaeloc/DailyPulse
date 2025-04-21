//
//  AboutScreen.swift
//  iosApp
//
//  Created by Rapha on 20/04/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct AboutScreen: View {
    @Environment(\.dismiss) private var dismiss
    
    var body: some View {
        NavigationStack {
            AboutView()
                .navigationTitle("About Device")
                .toolbar {
                    ToolbarContentView(dismiss)
                }
        }
    }
}

private struct AboutView: View {
    private let items: [AboutInformation] = {
        AboutData().informations()
    }()
    
    var body: some View {
        List {
            ForEach(items, id: \.self) { item in
                RowView(title: item.title, subtitle: item.subtitle)
            }
        }
    }
}

private struct RowView: View {
    let title: String
    let subtitle: String
    
    var body: some View {
        VStack(alignment: .leading) {
            Text(title)
                .font(.headline)
            Text(subtitle)
                .font(.caption)
        }
        .padding(.vertical, 4)
    }
}

@ToolbarContentBuilder
private func ToolbarContentView(_ dismiss: DismissAction) -> some ToolbarContent {
    ToolbarItem(placement: .primaryAction) {
        Button {
            dismiss()
        } label: {
            Text("Done")
                .bold()
        }
    }
}
